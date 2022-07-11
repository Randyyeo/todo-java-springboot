package com.example.todoappservice.core.utils;

import com.example.todoappservice.core.exception.ConflictUserException;
import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionUtil {

    @ExceptionHandler({InternalServerException.class})
    public ResponseEntity<String> handleTaskServiceException(InternalServerException e) {
      return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({ConflictUserException.class})
    public ResponseEntity<String> handleConflictUserException(ConflictUserException e) {
      return error(CONFLICT, e);
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e){
      return error(NOT_FOUND, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
      return ResponseEntity.status(status).body(e.getMessage());
    }
}
