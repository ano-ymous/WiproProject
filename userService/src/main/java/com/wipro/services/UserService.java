package com.wipro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.wipro.error.UserAlreadyExist;
import com.wipro.error.UserDoesNotExist;
import com.wipro.model.Users;

@Service
public interface UserService {
	public Users getUserById(String id) throws UserDoesNotExist;
 	
	public Users getUserByEmail(String email) throws UserDoesNotExist;
	
	public Users getUserByUsername(String username) throws UserDoesNotExist;
	
	public void deleteUser(Users existingUser) throws IllegalArgumentException;
	
	public void saveUser(Users user) throws IllegalArgumentException;
	
	public void insertUser(Users user) throws UserAlreadyExist;
}
