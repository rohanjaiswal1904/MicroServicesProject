package com.microservices.user.service.UserService.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "User")
public class User {
	
	@Id
	@Column(name = "ID")
	private String userId;
	
	@Column (name = "NAME", length =  20)
	private String name;
	
	@Transient
	List<Rating> rating = new ArrayList();


}
