package com.orbit.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orbit.ecommerce.model.User;

public interface UsersRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
