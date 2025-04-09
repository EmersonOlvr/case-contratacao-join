package com.cases.join.publisher.error;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Builder
public class ErrorResponseException extends RuntimeException {

	private static final long serialVersionUID = -4596080165511797271L;
	
	private final ErrorCode errorCode;
	private HttpStatus httpStatus;
	private String message;
	private final LinkedHashMap<String, Object> extra = new LinkedHashMap<>();

	private ErrorResponseException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.httpStatus = errorCode.getDefaultStatus();
		this.message = errorCode.getDefaultMessage();
	}

	public static ErrorResponseException of(ErrorCode errorCode){
		return new ErrorResponseException(errorCode);
	}

	public ErrorResponseException withMessage(String customMessage) {
		this.message = customMessage;
		return this;
	}

	public ErrorResponseException withHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
		return this;
	}

	public ErrorResponseException withExtraData(String key, Object value) {
		this.extra.put(key, value);
		return this;
	}

	public ErrorResponseException withResponseDetails(String errorBody){
		this.extra.put("responseDetails", errorBody);
		return this;
	}

	public ErrorResponseException withTitle(String title) {
		this.extra.put("title", title);
		return this;
	}

	public ErrorResponseException withDescription(String description) {
		this.extra.put("description", description);
		return this;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Map<String, Object> getExtraData() {
		return extra;
	}

	public ErrorResponseException apply(Consumer<ErrorResponseException> consumer){
		consumer.accept(this);
		return this;
	}

}
