package com.example.cetelem.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cetelem.web.exceptions.EntityNotFoundException;
import com.example.cetelem.web.exceptions.ErrorResponse;

/**
 * Maps exceptions to HTTP codes
 */
@RestControllerAdvice
public class EntityControllerExceptionHandler {
	
  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse EntityNotFound(EntityNotFoundException ex)
  {
      return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }
}
