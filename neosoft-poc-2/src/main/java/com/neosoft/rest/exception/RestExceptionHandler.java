package com.neosoft.rest.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// add an exception handler using @ExceptionHandler
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException exc) {

		log.error(exc.getMessage());

		return sendErrorResponse(HttpStatus.NOT_FOUND, exc.getMessage());
	}


	// handle validation errors
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		// Collect all the validation error messages
		List<String> errorMessages = ex.getBindingResult().getAllErrors().stream().map(err -> err.getDefaultMessage())
				.collect(Collectors.toList());

		log.error(errorMessages.toString());

		// create a ErrorResponse
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(errorMessages.toString());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// add another exception handler ... to catch any exception (catch all)
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc) {

		log.error(exc.getMessage());

		return sendErrorResponse(HttpStatus.BAD_REQUEST, exc.getMessage());
	}

	private static ResponseEntity<ErrorResponse> sendErrorResponse(HttpStatus status, String message) {
		// create a ErrorResponse
		ErrorResponse error = new ErrorResponse();

		error.setStatus(status.value());
		error.setMessage(message);
		error.setTimeStamp(System.currentTimeMillis());

		// return ResponseEntity
		return new ResponseEntity<>(error, status);
	}

}
