package com.cases.join.publisher.exception.http;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 6578748609215049351L;
	
	private HttpStatus httpStatus;
	
	public HttpRuntimeException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
	
}
