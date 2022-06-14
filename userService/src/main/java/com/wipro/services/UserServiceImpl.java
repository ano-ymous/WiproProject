package com.wipro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.error.CustomError;
import com.wipro.error.UserAlreadyExist;
import com.wipro.error.UserDoesNotExist;
import com.wipro.model.Users;
import com.wipro.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public Users getUserById(String id) throws UserDoesNotExist {
		// TODO Auto-generated method stub
		Optional<Users> user = userRepo.findById(id);
		return user.orElseThrow(UserDoesNotExist::new);
	}

	@Override
	public Users getUserByEmail(String email) throws UserDoesNotExist {
		// TODO Auto-generated method stub
		Optional<Users> user = userRepo.findByEmail(email);
		if(user.isEmpty())
			throw new UserDoesNotExist("No user exist with given email");
		return user.get();
	}

	@Override
	public Users getUserByUsername(String username) throws UserDoesNotExist {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username).orElseThrow(UserDoesNotExist::new);
	}

	@Override
	public void deleteUser(Users existingUser) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		try {
			userRepo.delete(existingUser);
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	@Override
	public void saveUser(Users user) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		try {
			userRepo.save(user);
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		
	}

	@Override
	public void insertUser(Users user) throws UserAlreadyExist {
		// TODO Auto-generated method stub
		try {
			userRepo.insert(user);
		}
		catch(Exception e) {
			throw new UserAlreadyExist(e.getMessage());
		}
	}

}
