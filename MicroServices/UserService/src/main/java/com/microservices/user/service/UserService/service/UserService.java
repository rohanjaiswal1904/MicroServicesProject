package com.microservices.user.service.UserService.service;

import java.util.List;

import com.microservices.user.service.UserService.entity.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String id);

}
