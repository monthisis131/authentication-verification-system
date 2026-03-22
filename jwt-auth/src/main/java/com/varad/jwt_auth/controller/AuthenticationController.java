package com.varad.jwt_auth.controller;

import com.varad.jwt_auth.dto.LoginUserDto;
import com.varad.jwt_auth.dto.RegisterUserDto;
import com.varad.jwt_auth.dto.VerifyUserDto;
import com.varad.jwt_auth.model.User;
import com.varad.jwt_auth.responses.LoginResponse;
import com.varad.jwt_auth.services.AuthenticationService;
import com.varad.jwt_auth.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final JwtService jwtService;
	private final AuthenticationService authenticationService;

	// SIGNUP
	@PostMapping("/signup")
	public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {

		User registeredUser = authenticationService.signup(registerUserDto);
		return ResponseEntity.ok(registeredUser);
	}

	// LOGIN
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

		User authenticatedUser = authenticationService.authenticate(loginUserDto);

		String jwtToken = jwtService.generateToken(authenticatedUser);

		LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}

	// VERIFY OTP
	@PostMapping("/verify")
	public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {

		try {
			authenticationService.verifyUser(verifyUserDto);
			return ResponseEntity.ok("Account verified successfully");

		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// RESEND OTP
	@PostMapping("/resend")
	public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {

		try {
			authenticationService.resendVerificationCode(email);
			return ResponseEntity.ok("Verification code sent");

		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}