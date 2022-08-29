package com.codewithshaheen.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithshaheen.blog.entities.User;
import com.codewithshaheen.blog.exceptions.ResourceNotFoundException;
import com.codewithshaheen.blog.payload.UserDto;
import com.codewithshaheen.blog.repositories.UserRepo;
import com.codewithshaheen.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepo userRepo; 
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		
		return this.UserToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1= this.UserToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserbyId(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.UserToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List <User> users = this.userRepo.findAll();
		List <UserDto> userDto= users.stream().map(user-> this.UserToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);
	}
	
	public User dtoToUser (UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		return user;
		
	}
	public UserDto UserToDto (User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
		
		
	}

}
