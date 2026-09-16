package com.orbit.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orbit.ecommerce.dto.LoginRequestDto;
import com.orbit.ecommerce.dto.RegisterRequestDto;
import com.orbit.ecommerce.dto.UserResponseDto;
import com.orbit.ecommerce.exception.InvalidEmailException;
import com.orbit.ecommerce.exception.InvalidPasswordException;
import com.orbit.ecommerce.exception.UserAlreadyPresentException;
import com.orbit.ecommerce.service.UsersService;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private final UsersService usersService;
	
	public UsersController(UsersService usersService) {
		this.usersService=usersService;
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> registerUser(@RequestBody RegisterRequestDto request) throws UserAlreadyPresentException {
		return ResponseEntity.ok(usersService.registerUser(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto request) throws InvalidPasswordException, InvalidEmailException {
		return ResponseEntity.ok(usersService.login(request));
	}
}
