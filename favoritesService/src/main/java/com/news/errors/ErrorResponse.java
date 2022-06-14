package com.news.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ErrorResponse {
	// customizing timestamp serialization format
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	@JsonFormat(shape=JsonFormat.Shape.NUMBER_INT)
	private int code;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String status;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private String message;
	@JsonFormat(shape=JsonFormat.Shape.OBJECT)
	private Object data;

	public ErrorResponse() {
		timestamp = LocalDateTime.now();
	}

	public ErrorResponse(HttpStatus httpStatus, String message) {
		this();
		this.code = httpStatus.value();
		this.status = httpStatus.name();
		this.message = message;
	}

	public ErrorResponse(HttpStatus httpStatus, String message, String stackTrace, Object data) {
		this(httpStatus, message);
		this.data = data;
	}
	
}