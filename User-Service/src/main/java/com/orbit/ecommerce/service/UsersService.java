package com.orbit.ecommerce.service;

import org.springframework.stereotype.Service;

import com.orbit.ecommerce.dto.LoginRequestDto;
import com.orbit.ecommerce.dto.RegisterRequestDto;
import com.orbit.ecommerce.dto.UserResponseDto;
import com.orbit.ecommerce.exception.InvalidEmailException;
import com.orbit.ecommerce.exception.InvalidPasswordException;
import com.orbit.ecommerce.exception.UserAlreadyPresentException;

public interface UsersService {

	UserResponseDto registerUser(RegisterRequestDto request) throws UserAlreadyPresentException;
	UserResponseDto login(LoginRequestDto request) throws InvalidPasswordException, InvalidEmailException;
}
