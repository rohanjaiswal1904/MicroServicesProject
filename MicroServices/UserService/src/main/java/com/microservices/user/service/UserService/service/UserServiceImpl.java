
package com.microservices.user.service.UserService.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.user.service.UserService.entity.Hotel;
import com.microservices.user.service.UserService.entity.Rating;
import com.microservices.user.service.UserService.entity.User;
import com.microservices.user.service.UserService.exception.ResourceNotFoundException;
import com.microservices.user.service.UserService.externalservice.HotelService;
import com.microservices.user.service.UserService.repositry.UserRepositry;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepositry userRepositry;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); // get all user

	@Override
	public List<User> getAllUser() {

		List<User> list = userRepositry.findAll();
		return list;

	}

	@Override
	public User saveUser(User user) {

		String rendomUserId = UUID.randomUUID().toString();
		user.setUserId(rendomUserId);
		return userRepositry.save(user);
	}

	@Override
	public User getUser(String id) {
//get user from database with the help of uper repositry
		User user = userRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException());


		logger.info(user.toString());
		// fetch rating of above user from rating service
     
	Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId(), Rating[].class);
		  
	logger.info("{}", ratingOfUser);
	
	List<Rating> ratings = Arrays.asList(ratingOfUser);
		//calling external services api using RestTemplate
		//ratings.forEach(x -> x.setHotel(restTemplate.getForObject("http://HOTEL-SERVICE/hotels/"+x.getHotelId(), Hotel.class )));
	
	//calling external services api using Feign Client
	ratings.forEach(x -> x.setHotel(hotelService.getHotel(x.getHotelId())));

	
	user.setRating(ratings);
		return user;
	}
}
