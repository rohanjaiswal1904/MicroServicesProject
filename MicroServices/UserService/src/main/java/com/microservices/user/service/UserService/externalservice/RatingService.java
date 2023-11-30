package com.microservices.user.service.UserService.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.microservices.user.service.UserService.entity.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("/ratings/{ratingId}")
	Rating getSingleRating(@PathVariable String ratingId);
	
	@PostMapping("/ratings")
	Rating createRating(Rating rating);
	
	@PutMapping("/ratings/{ratingId}")
	Rating updateRating(@PathVariable String ratingId, Rating rating);

	@DeleteMapping("/ratings/{ratingId}")
	void deleteRating(@PathVariable String ratingId);
}
