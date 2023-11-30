
  package com.microservices.user.service.UserService.repositry;
  
  import org.springframework.data.jpa.repository.JpaRepository; import
  org.springframework.stereotype.Repository;
  
  import com.microservices.user.service.UserService.entity.User;
  
  @Repository public interface UserRepositry extends JpaRepository<User,
  String> {
  
  }
 