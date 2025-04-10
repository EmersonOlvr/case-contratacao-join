package com.cases.join.publisher.error;

import java.util.List;
import java.util.Map;

import com.cases.join.publisher.exception.ValidationHandler.FieldErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {

	private String timestamp;
	private int status;
	private List<FieldErrorResponse> errors;
	public ErrorCode errorCode;
	public String message;
	private String path;
	public Map<String, Object> extra;

}
