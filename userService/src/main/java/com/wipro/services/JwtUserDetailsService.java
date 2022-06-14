package com.wipro.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wipro.configuration.ApplicationContextHolder;
import com.wipro.model.UserCredentialModel;
import com.wipro.repository.UserCredRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserCredRepository credRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		credRepo = ApplicationContextHolder.getContext().getBean(UserCredRepository.class);
		Optional<UserCredentialModel> user = credRepo.findByEmail(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		else {
			return new User(user.get().getEmail(),user.get().getPassword(),new ArrayList<>());
		}
	}

}