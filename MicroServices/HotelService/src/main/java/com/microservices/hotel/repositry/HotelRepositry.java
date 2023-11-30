package com.microservices.hotel.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.hotel.entity.Hotel;

@Repository
public interface HotelRepositry  extends JpaRepository<Hotel, String>{

}
