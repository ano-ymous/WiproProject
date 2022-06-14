package com.wipro.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class ResponseService {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private String data;
	 
	ResponseService(){
		timestamp = LocalDateTime.now();
	}
	public ResponseService(String data){
		this();
		this.data = data;
	}
}
