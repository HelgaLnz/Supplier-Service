package com.example.supplierservice.exception;


public class ObjectAlreadyExistsException extends RuntimeException {
  public ObjectAlreadyExistsException(String message) {
    super(message);
  }
}
