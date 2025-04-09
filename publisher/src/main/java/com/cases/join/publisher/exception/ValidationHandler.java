package com.cases.join.publisher.exception;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.cases.join.publisher.error.ErrorCode;
import com.cases.join.publisher.error.ErrorResponseDTO;
import com.cases.join.publisher.serialization.OffsetDateTimeSerializer;

@RestControllerAdvice
public class ValidationHandler {
	
	public record FieldErrorResponse(String field, String message) {}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
		List<FieldErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> new FieldErrorResponse(err.getField(), err.getDefaultMessage()))
				.toList();

    	return ResponseEntity.status(ex.getStatusCode().value())
				.body(ErrorResponseDTO.builder()
							.timestamp(OffsetDateTime.now().format(OffsetDateTimeSerializer.ISO_8601_FORMATTER))
							.status(ex.getStatusCode().value())
							.errors(fieldErrors)
							.errorCode(ErrorCode.BAD_REQUEST)
							.message("Erro de validação nos campos.")
							.path(request.getDescription(false).substring(4))
							.extra(new LinkedHashMap<>())
							.build()
				);
    }
	
}