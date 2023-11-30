package com.microservices.user.service.UserService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.user.service.UserService.entity.User;
import com.microservices.user.service.UserService.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("users")
public class UserController {
	

	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		
		return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
	}

	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId){
		
		System.out.println("Userid ---------------" +userId);
		
		return ResponseEntity.ok(userService.getUser(userId));
		
	}
	
	//creating fall back  method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
 //       logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        User user = User.builder().name("Dummy user beacause some service is down").userId("121").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		
		return ResponseEntity.ok(userService.getAllUser());
		
	}
}
