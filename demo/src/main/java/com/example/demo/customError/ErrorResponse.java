package com.example.demo.customError;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.ErrorModel;

import jakarta.annotation.Nullable;

@ControllerAdvice
public class ErrorResponse extends ResponseEntityExceptionHandler{

	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
		List<String> errors = ex.getFieldErrors().stream().map(x-> x.getDefaultMessage()).collect(Collectors.toList()); 
		ErrorModel errorResponse = ErrorModel.builder().code(status.value()).message(errors).details(request.getDescription(true)).build();
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
