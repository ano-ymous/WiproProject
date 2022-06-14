package com.wipro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.error.UserAlreadyExist;
import com.wipro.error.UserDoesNotExist;
import com.wipro.model.UserCredentialModel;
import com.wipro.repository.UserCredRepository;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
	@Autowired
	UserCredRepository userCredRepo;

	@Override
	public UserCredentialModel getUserCredByEmail(String email) throws UserDoesNotExist {
		// TODO Auto-generated method stub
		return userCredRepo.findById(email).orElseThrow(UserDoesNotExist::new);
	}

	@Override
	public void insertUserCred(UserCredentialModel userCred) throws UserAlreadyExist {
		// TODO Auto-generated method stub
		try {
			userCredRepo.insert(userCred);
		}
		catch(Exception e) {
			throw new UserAlreadyExist(e.getMessage());
		}
	}

	@Override
	public void deleteUserCred(UserCredentialModel userCred) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		userCredRepo.delete(userCred);
	}

	@Override
	public void saveUserCred(UserCredentialModel userCred) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		userCredRepo.save(userCred);
	}

}
