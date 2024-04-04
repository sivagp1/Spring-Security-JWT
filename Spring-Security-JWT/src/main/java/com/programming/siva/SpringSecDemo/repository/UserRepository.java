package com.programming.siva.SpringSecDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programming.siva.SpringSecDemo.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);

}
