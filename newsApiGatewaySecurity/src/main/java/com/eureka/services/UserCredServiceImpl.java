package com.eureka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.repository.UserCredRepository;

@Service
public class UserCredServiceImpl implements UserCredService {

	@Autowired
	private UserCredRepository userCredRepo;
	
	@Override
	public boolean isUserExist(String username){
		// TODO Auto-generated method stub
		return userCredRepo.existsById(username);
	}
}
