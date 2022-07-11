package com.example.todoappservice.core.utils;

import com.example.todoappservice.server.task.Task;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public abstract class ResponseUtil {

  private final static String responseConflictEmail = "User already exists for email: ";
  private final static String responseCreated = "User has been created for Id: ";
  private final static String responseInternalServerError = "Internal Server Error has occured";
  private final static String responseUserNotFound = "User cannot be found with email: ";
  private final static String responseTaskNotFound = "Task cannot be found for Id: ";
  private final static String responseTaskDeleted = "Task has been deleted for Id: ";
  private final static String responseTaskUpdated = "Task has been updated for Id: ";
  private final static String responseNotAuthorized = "User not authorized";
  private final static String responseLoginSuccess = "User has login successfully for Id: ";

  public static ResponseDto createReturnValue(String message){
    ResponseDto response = new ResponseDto();
    response.setMessage(message);
    return response;
  }

  public static ResponseDto createReturnValue(String message, String token){
    ResponseDto response = new ResponseDto();
    response.setMessage(message);
    response.setToken(token);
    return response;
  }
  public static ResponseEntity<Task> responseOk(Task task){
    return ResponseEntity.status(HttpStatus.OK)
            .body(task);
  }

  public static ResponseEntity responseTaskDeleted(Long Id){
    ResponseDto body = createReturnValue(responseTaskDeleted + Id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(body);
  }

  public static ResponseEntity responseTaskUpdated(Long Id){
    ResponseDto body = createReturnValue(responseTaskUpdated + Id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(body);
  }

  public static ResponseEntity responseTaskNotFound(Long Id){
    ResponseDto body = createReturnValue(responseTaskNotFound + Id);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(body);
  }

  public static ResponseEntity responseUserNotFound(String email){
    ResponseDto body = createReturnValue(responseUserNotFound + email);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(body);
  }

  public static ResponseEntity responseConflict(String email){
    ResponseDto body = createReturnValue(responseConflictEmail + email);
    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(body);
  }

  public static ResponseEntity responseTaskCreated(Long Id) {
    ResponseDto body = createReturnValue(responseCreated + Id);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseUserCreated(Long Id, String token) {
    ResponseDto body = createReturnValue(responseCreated + Id, token);
    System.out.println(body);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseLoginSuccess(Long Id, String token) {
    ResponseDto body = createReturnValue(responseLoginSuccess + Id, token);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseInternalServerError() {
    ResponseDto body = createReturnValue(responseInternalServerError);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(body);
  }

  public static ResponseEntity responseNotAuthorized(){
    ResponseDto body = createReturnValue(responseNotAuthorized);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(body);
  }
}
