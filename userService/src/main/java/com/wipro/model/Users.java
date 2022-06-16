package com.wipro.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Document(collection="userdetails")
@Accessors(chain = true)
@Data
public class Users {
	@Id
	private String username;
	
	private String firstname;
	private String lastname;
	
	@Indexed(unique = true)
	private String email;
	@Indexed(unique=true)
	private long phonenumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateofbirth;
	
	private String gender;
	private String nation;
	
	public Users() {
		
	}
	
	public Users(String username, String firstname, String lastname, String email, long phonenumber, Date dateofbirth,
			String gender, String nation) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phonenumber = phonenumber;
		this.dateofbirth = dateofbirth;
		this.gender = gender;
		this.nation = nation;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getDateOfBirth() {
		return dateofbirth;
	}
	public void setDateOfBirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
}
