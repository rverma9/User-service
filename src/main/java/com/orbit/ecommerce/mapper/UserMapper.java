package com.orbit.ecommerce.mapper;

import org.springframework.stereotype.Component;

import com.orbit.ecommerce.dto.UserResponseDto;
import com.orbit.ecommerce.model.User;


@Component
public class UserMapper {
	
	public UserResponseDto mapToDto(User user) {
		
		UserResponseDto dto = new UserResponseDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setRole(user.getRole());
		dto.setCreatedAt(user.getCreatedAt());
		dto.setUpdatedAt(user.getUpdatedAt());
		
		return dto;
	}
}