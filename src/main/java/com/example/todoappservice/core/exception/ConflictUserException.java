package com.example.todoappservice.core.exception;

public class ConflictUserException extends RuntimeException {
  public ConflictUserException(String message) {
    super(message);
  }
}
