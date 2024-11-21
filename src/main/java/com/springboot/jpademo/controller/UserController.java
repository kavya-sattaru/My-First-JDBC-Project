package com.springboot.jpademo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpademo.dto.ResponseDTO;
import com.springboot.jpademo.dto.UserDTO;
import com.springboot.jpademo.model.User;
import com.springboot.jpademo.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseDTO addUsers(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	

	@GetMapping("/user")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PutMapping("/user/{userId}")
	public ResponseDTO updateUsers(@PathVariable("userId") Integer userId,@RequestBody UserDTO userDTO) {
		return userService.updateUsers(userId,userDTO);
		
	}
	@DeleteMapping("/user/{userId}")
	public ResponseDTO deleteUsers(@PathVariable("userId") Integer userId) {
		return userService.deleteUsers(userId);
		
	}
}
