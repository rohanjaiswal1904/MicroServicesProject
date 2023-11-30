package com.microservices.rating.repositry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservices.rating.entity.Rating;

@Repository
public interface RatingRepositry extends MongoRepository<Rating,String>{

	List<Rating> findByHotelId(String hotelId);

	List<Rating> findByUserId(String userId);

}
