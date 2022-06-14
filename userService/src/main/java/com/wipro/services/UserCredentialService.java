package com.wipro.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wipro.error.UserAlreadyExist;
import com.wipro.error.UserDoesNotExist;
import com.wipro.model.UserCredentialModel;

@Service
public interface UserCredentialService {
	public UserCredentialModel getUserCredByEmail(String email) throws UserDoesNotExist;
	
	public void insertUserCred(UserCredentialModel userCred) throws UserAlreadyExist;
	
	public void deleteUserCred(UserCredentialModel userCred) throws IllegalArgumentException;
	
	public void saveUserCred(UserCredentialModel userCred) throws IllegalArgumentException;
}
