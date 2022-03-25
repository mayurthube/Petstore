package com.petstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.petstore.model.User;
import com.petstore.model.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	public User save(User user) {
		return userRepo.save(user);
	}
	public Object saveAll(User[] users) {
		return userRepo.saveAll(users);
	}
	public void saveAll(List<User> users) {
		for (User user : users) {
			userRepo.save(user);
		}
	}
	public User finByUsername(String username) {
		return userRepo.findByUserName(username);		
	}
	public void deleteByName(String username) {
		userRepo.deleteByUsername(username);	
	}
	public User update(User user) {
		
		return userRepo.save(user);
	}

}
