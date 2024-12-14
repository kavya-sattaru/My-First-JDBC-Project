package com.springboot.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.inventory.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
}
