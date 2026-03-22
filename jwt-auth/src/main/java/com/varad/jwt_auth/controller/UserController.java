package com.varad.jwt_auth.controller;

import com.varad.jwt_auth.model.User;
import com.varad.jwt_auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	// get current logged-in user
	@GetMapping("/me")
	public ResponseEntity<User> authenticatedUser(Authentication authentication) {

		User currentUser = (User) authentication.getPrincipal();

		return ResponseEntity.ok(currentUser);
	}

	// get all users (protected)
	@GetMapping
	public ResponseEntity<List<User>> allUsers() {

		List<User> users = userService.allUsers();

		return ResponseEntity.ok(users);
	}
}