package com.example.genestore.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.genestore.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
    Collection<User> findAllByRole(String role);
}