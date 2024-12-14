package com.springboot.inventory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventory.dto.ResponseDTO;
import com.springboot.inventory.dto.UserDTO;
import com.springboot.inventory.model.User;
import com.springboot.inventory.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseDTO addUsers(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	@GetMapping
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PutMapping("/{userId}")
	public ResponseDTO updateUsers(@PathVariable("userId") Integer userId,@RequestBody UserDTO userDTO) {
		return userService.updateUsers(userId,userDTO);
		
	}
	@DeleteMapping("/{userId}")
	public ResponseDTO deleteUsers(@PathVariable("userId") Integer userId) {
		return userService.deleteUsers(userId);
	}
	@GetMapping("/{userId}")
	public Optional<User> getUser(@PathVariable("userId") Integer userId) {
		return userService.getUser(userId);
	}
	
}
