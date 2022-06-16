package com.wipro.controllers;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.authentication.util.JwtTokenUtil;
import com.wipro.error.CustomError;
import com.wipro.error.UserAlreadyExist;
import com.wipro.error.UserDoesNotExist;
import com.wipro.model.UserCredentialModel;
import com.wipro.model.Users;
import com.wipro.services.AuthorizationDataModelService;
import com.wipro.services.ResponseService;
import com.wipro.services.UserCredentialServiceImpl;
import com.wipro.services.UserServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	@Autowired
	UserCredentialServiceImpl credService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	
	@GetMapping(value = "/getuserdetails/{username}")
	public ResponseEntity<Users> getDetails(
			@PathVariable("username") String username) 
			throws CustomError {
		Users user = null;
		if(username!=null) {
			user = userService.getUserById(username.toLowerCase());
		}
		else {
			throw new UserDoesNotExist(String.format("No account with username %s exist",username));
		}
		return ResponseEntity.accepted().body(user);
	}
	
	@PostMapping(value="/setuserdetials/{username}")
	public ResponseEntity<ResponseService> setDetails(
			@PathVariable("username") String username,
			@RequestBody Users updatedDetails) {
		username = username.toLowerCase();
		if(!username.equals(updatedDetails.getUsername())) {
			Users existingDetails = 
					userService.getUserById(username);
			userService.deleteUser(existingDetails);
			userService.saveUser(updatedDetails);
		}
		else {
			userService.saveUser(updatedDetails);
		}
		return ResponseEntity.ok(new ResponseService("Changes updated"));
	}
	
	@PostMapping(value = "/changeemail/{username}")
	public ResponseEntity<ResponseService> changeEmail(
			@PathVariable("username") String username,
			@RequestPart("oldDetails") UserCredentialModel oldCred,
			@RequestPart("newEmail") String newEmail) throws Exception {
		oldCred.setEmail(oldCred.getEmail().toLowerCase());
		username = username.toLowerCase();
		newEmail = newEmail.toLowerCase();
		
		authenticate(oldCred.getEmail(),oldCred.getPassword());
		
		Users userByUsername = userService.getUserByEmail(oldCred.getEmail());
		Users userByEmail = userService.getUserByEmail(newEmail);
		
		UserCredentialModel credByEmail = credService.getUserCredByEmail(oldCred.getEmail());
		try {
			userByUsername.setEmail(newEmail);
			userService.saveUser(userByUsername);
			
			credService.deleteUserCred(credByEmail);
			credByEmail.setEmail(newEmail);
			credService.saveUserCred(credByEmail);
		}
		catch(Exception e){
			throw new IllegalArgumentException("New Email Id is already taken by other user");
		}
		
		return ResponseEntity.accepted().body(new ResponseService("Email updated"));
	}
	
	@PostMapping(value = "/setpassword")
	public ResponseEntity<ResponseService> changePassword(
			@RequestPart("oldCredentials") UserCredentialModel oldCred,
			@RequestPart("newPassword") String newPassword) throws Exception {
		oldCred.setEmail(oldCred.getEmail().toLowerCase());
		
		authenticate(oldCred.getEmail(),oldCred.getPassword());
		
		oldCred.setPassword(PasswordEncoderFactories
				.createDelegatingPasswordEncoder()
				.encode(newPassword).substring(8));
		System.out.println(oldCred.getPassword());
		credService.saveUserCred(oldCred);
		
		return ResponseEntity.accepted().body(new ResponseService("password has been changed"));
	}
	
	@PostMapping(value = "/login",consumes = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AuthorizationDataModelService> login(
			@RequestBody UserCredentialModel loginCred)
					throws Exception {
		loginCred.setEmail(loginCred.getEmail().toLowerCase());
		
		authenticate(loginCred.getEmail(), loginCred.getPassword());
		
		Users user = userService.getUserByEmail(loginCred.getEmail());
		
		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(loginCred.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthorizationDataModelService(
				user.getUsername(),token,LocalDateTime.now()
				.plusHours(5),user.getNation()));
	}

	@PostMapping(value = "/register",consumes = { MediaType.APPLICATION_JSON_VALUE, 
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ResponseService> register(@RequestPart("user") Users user,
			@RequestPart("cred") UserCredentialModel cred) throws Exception {
		if(!user.getEmail().equals(cred.getEmail()))
			throw new CustomError("User email and Credential email should be same");
		try {
			user = this.convert(user);
			userService.insertUser(user);
		} catch (Exception e) {
			throw new UserAlreadyExist(e.getMessage());
		}
		boolean match = cred.getPassword()
				.matches("^((?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[$@$!%*#?&]))[A-Za-z\\d$@$!%*#?&]{8,}$");
		if(!match)
			throw new CustomError("Credentials does not match with the criteria");
		cred.setPassword(PasswordEncoderFactories
				.createDelegatingPasswordEncoder()
				.encode(cred.getPassword()).substring(8));
		cred.setEmail(cred.getEmail().toLowerCase());
		credService.insertUserCred(cred);
		return ResponseEntity.accepted().body(new ResponseService("user successfully registered"));
	}
	
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	private Users convert(Users user) {
		user.setUsername(user.getUsername().toLowerCase());
		user.setEmail(user.getEmail().toLowerCase());
		return user;
	}
}
