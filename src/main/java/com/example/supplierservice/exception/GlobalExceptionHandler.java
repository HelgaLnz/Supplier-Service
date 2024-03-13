package com.example.supplierservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  @ResponseBody
  public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(exception.getMessage());
  }

  @ExceptionHandler(ObjectAlreadyExistsException.class)
  @ResponseBody
  public ResponseEntity<Object> handleObjectAlreadyExistsException(ObjectAlreadyExistsException exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(exception.getMessage());
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(exception.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    return ResponseEntity
      .badRequest()
      .body(ex.getBindingResult()
        .getAllErrors()
        .stream()
        .map(FieldError.class::cast)
        .collect(Collectors.toMap(FieldError::getField, ObjectError::getDefaultMessage)));
  }
}
