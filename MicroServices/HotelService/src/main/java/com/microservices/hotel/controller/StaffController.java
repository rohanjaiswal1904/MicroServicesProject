package com.microservices.hotel.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {
	
	@GetMapping
	public ResponseEntity<List<String>> getStaff(){
		
		System.out.println("ececuted..............");
		List<String> list = Arrays.asList("Ramesh", "Suresh", "Sita", "Geeta");
	
		return ResponseEntity.ok(list);
	}

}
