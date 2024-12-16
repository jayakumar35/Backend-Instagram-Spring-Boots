package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.InstaUser;

public interface InstaRepository extends JpaRepository<InstaUser, Integer> {
	InstaUser findByUsername(String username);

	InstaUser findByEmail(String email);

	Optional<InstaUser> findById(Long id);

}
