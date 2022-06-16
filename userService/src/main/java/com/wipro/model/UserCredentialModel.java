package com.wipro.model;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.experimental.Accessors;

@Document(collection="login")
@Accessors(chain = true)
@Data
public class UserCredentialModel {
	@Id
	private String email;
	private String password;
	public UserCredentialModel() {
		
	}
	public UserCredentialModel(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserCredentialModel [ email=" + email + ", password=" + password + "]";
	}
	

}
