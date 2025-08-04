package com.project1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {


	// Handle custom exception - FrameNotFoundException
	@ExceptionHandler(FrameNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleFrameNotFoundException(FrameNotFoundException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("error", "Frame Not Found");
		response.put("message", ex.getMessage());
		response.put("status", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	// Handle general exceptions (e.g., IllegalArgumentException)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("error", "Invalid Input");
		response.put("message", ex.getMessage());
		response.put("status", HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("error", "Validation Error");
		response.put("status", HttpStatus.BAD_REQUEST.value());

		// Extract field-specific validation errors
		Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
				error -> error.getField(), error -> error.getDefaultMessage(), (existing, replacement) -> existing // Ignore
																													// duplicate
																													// errors
		));

		response.put("errors", fieldErrors);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	//"errors": {
    // "email": "Email must be valid",
    // "password": "Password must be at least 8 characters"
// }

	// Handle general exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("error", "Internal Server Error");
		response.put("message", ex.getMessage());
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
