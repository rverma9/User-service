package com.orbit.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orbit.ecommerce.dto.LoginRequestDto;
import com.orbit.ecommerce.dto.RegisterRequestDto;
import com.orbit.ecommerce.dto.UserResponseDto;
import com.orbit.ecommerce.exception.InvalidEmailException;
import com.orbit.ecommerce.exception.InvalidPasswordException;
import com.orbit.ecommerce.exception.UserAlreadyPresentException;
import com.orbit.ecommerce.mapper.UserMapper;
import com.orbit.ecommerce.model.Role;
import com.orbit.ecommerce.model.User;
import com.orbit.ecommerce.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	private final UsersRepository usersRepository;
	private final PasswordEncoder encoder;
	private final UserMapper mapper;
	
	public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder encoder, UserMapper mapper) {
		this.usersRepository = usersRepository;
		this.encoder = encoder;
		this.mapper=mapper;
	}
	
	@Override
	public UserResponseDto registerUser(RegisterRequestDto request) throws UserAlreadyPresentException {
		
		if(usersRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new UserAlreadyPresentException("User already present");
		}
		
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		if (request.getRole() != null) {
	        user.setRole(request.getRole());
	    } else {
	        user.setRole(Role.CUSTOMER);
	    }
		
		User savedUser = usersRepository.save(user);
		
		UserResponseDto response = mapper.mapToDto(savedUser);
		return response;
		
	}

	@Override
	public UserResponseDto login(LoginRequestDto request) throws InvalidPasswordException, InvalidEmailException {
		
		User user = usersRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidEmailException("No user with this email"));
		
		boolean validatePassword = encoder.matches(request.getPassword(), user.getPassword());
		
		if(!validatePassword) {
			 throw new InvalidPasswordException("Invalid password");
		}
		
		UserResponseDto response = mapper.mapToDto(user);
		return response;
	}

}
