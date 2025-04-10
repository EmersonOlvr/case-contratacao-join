package com.cases.join.publisher.error;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request Error"),

	// Permissions
	YOU_DO_NOT_HAVE_THE_PERMISSION_TO_DO_THAT(HttpStatus.FORBIDDEN);

	private final HttpStatus defaultStatus;
	private final String defaultMessage;

	private ErrorCode(HttpStatus defaultStatus, String defaultMessage) {
		this.defaultStatus = defaultStatus;
		this.defaultMessage = defaultMessage;
	}

	private ErrorCode(HttpStatus defaultStatus) {
		this.defaultStatus = defaultStatus;
		this.defaultMessage = this.name();
	}

	public HttpStatus getDefaultStatus() {
		return defaultStatus;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public ErrorResponseException newError(){
		return ErrorResponseException.of(this);
	}
}
