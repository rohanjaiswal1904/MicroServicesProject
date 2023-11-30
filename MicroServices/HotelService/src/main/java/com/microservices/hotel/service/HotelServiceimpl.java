package com.microservices.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.hotel.entity.Hotel;
import com.microservices.hotel.exception.ResourceNotFoundException;
import com.microservices.hotel.repositry.HotelRepositry;

@Service
public class HotelServiceimpl implements HotelService{
	
	  @Autowired
	    private HotelRepositry hotelRepositry;

	    @Override
	    public Hotel create(Hotel hotel) {
	        String hotelId = UUID.randomUUID().toString();
	        hotel.setId(hotelId);
	        return hotelRepositry.save(hotel);
	    }

	    @Override
	    public List<Hotel> getAll() {
	        return hotelRepositry.findAll();
	    }

	    @Override
	    public Hotel get(String id) {
	        return hotelRepositry.findById(id).orElseThrow(() -> new ResourceNotFoundException("hotel with given id not found !!"));
	    }

		

}
