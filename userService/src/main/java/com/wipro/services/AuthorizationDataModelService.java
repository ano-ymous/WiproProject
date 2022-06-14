package com.wipro.services;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class AuthorizationDataModelService extends ResponseService {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime expiryDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String username;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String nation;
	public AuthorizationDataModelService(String username,String data,LocalDateTime expiryDate, String nation) {
		super(data);
		this.username = username;
		this.expiryDate = expiryDate;
		this.nation = nation;
		
	}
}
