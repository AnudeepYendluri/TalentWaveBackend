package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UserAuthenticationProvider;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User user1 = userService.addUser(user);
		user1.setToken(userAuthenticationProvider.createToken(user1.getEmail()));
		return ResponseEntity.ok(user1);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user){
		User user1 = userService.loginUser(user);
		user1.setToken(userAuthenticationProvider.createToken(user1.getEmail()));
		return ResponseEntity.ok(user1);
	}
}
