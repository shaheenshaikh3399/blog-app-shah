package com.codewithshaheen.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshaheen.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
