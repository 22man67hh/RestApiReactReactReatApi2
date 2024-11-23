package com.smart.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.smart.entity.User;
import com.smart.exception.UserNotFoundException;
import com.smart.repository.UserRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
	@Autowired 
	private UserRepo userRepo;
	@PostMapping("/user")
	User newUser(@RequestBody User newUser) {
		return userRepo.save(newUser);
	}
	
	@GetMapping("/users")
	List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	@GetMapping("/users/{id}")
	User getUserById(@PathVariable long id) {
		return userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
	}
	
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable long id) {
		return userRepo.findById(id).map(user->{
			user.setUsername(newUser.getUsername());
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			return userRepo.save(user);
		}).orElseThrow(()->new UserNotFoundException(id));
	}

	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable long id) {
		if(!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepo.deleteById(id);
		return "User with id "+id+" has been removed";
	}
}
