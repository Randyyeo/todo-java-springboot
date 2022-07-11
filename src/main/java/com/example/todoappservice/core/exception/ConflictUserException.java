package com.example.todoappservice.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictUserException extends RuntimeException {
  public ConflictUserException(String message) {
    super(message);
  }
}
