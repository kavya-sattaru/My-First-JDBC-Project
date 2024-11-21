package com.springboot.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.jpademo.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	
}
