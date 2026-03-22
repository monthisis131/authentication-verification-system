package com.varad.jwt_auth.services;

import com.varad.jwt_auth.dto.LoginUserDto;
import com.varad.jwt_auth.dto.RegisterUserDto;
import com.varad.jwt_auth.dto.VerifyUserDto;
import com.varad.jwt_auth.model.User;
import com.varad.jwt_auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    // SIGNUP
    public User signup(RegisterUserDto input) {

        User user = new User(
                null,
                input.getUsername(),
                input.getEmail(),
                passwordEncoder.encode(input.getPassword()),
                false,
                generateVerificationCode(),
                LocalDateTime.now().plusMinutes(15)
        );

        sendVerificationEmail(user);

        return userRepository.save(user);
    }

    // LOGIN
    public User authenticate(LoginUserDto input) {

        User user = userRepository
                .findByUsernameOrEmail(input.getUsername(), input.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please verify your account.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return user;
    }

    // VERIFY USER
    public void verifyUser(VerifyUserDto input) {

        Optional<User> optionalUser = userRepository.findByEmail(input.getEmail());

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }

            if (user.getVerificationCode().equals(input.getVerificationCode())) {

                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);

                userRepository.save(user);

            } else {
                throw new RuntimeException("Invalid verification code");
            }

        } else {
            throw new RuntimeException("User not found");
        }
    }

    // RESEND CODE
    public void resendVerificationCode(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if (user.isEnabled()) {
                throw new RuntimeException("Account already verified");
            }

            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));

            sendVerificationEmail(user);

            userRepository.save(user);

        } else {
            throw new RuntimeException("User not found");
        }
    }

    // SEND EMAIL
    private void sendVerificationEmail(User user) {

        String subject = "Account Verification";
        String verificationCode = user.getVerificationCode();

        String htmlMessage =
                "<html>" +
                "<body>" +
                "<h2>Verify your account</h2>" +
                "<p>Your verification code is:</p>" +
                "<h3>" + verificationCode + "</h3>" +
                "<p>This code will expire in 15 minutes.</p>" +
                "</body>" +
                "</html>";

        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email");
        }
    }

    // GENERATE CODE
    private String generateVerificationCode() {

        Random random = new Random();
        int code = random.nextInt(900000) + 100000;

        return String.valueOf(code);
    }
}