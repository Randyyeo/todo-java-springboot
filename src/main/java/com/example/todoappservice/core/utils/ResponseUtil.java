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

  public static HashMap<String, String> createReturnValue(String message){
    HashMap<String, String> map = new HashMap<>();
    map.put("message", message);
    return map;
  }

  public static HashMap<String, String> createReturnValue(String message, String token){
    HashMap<String, String> map = new HashMap<>();
    map.put("message", message);
    map.put("token", token);
    return map;
  }
  public static ResponseEntity<Task> responseOk(Task task){
    return ResponseEntity.status(HttpStatus.OK)
            .body(task);
  }

  public static ResponseEntity responseTaskDeleted(Long Id){
    HashMap<String, String> body = createReturnValue(responseTaskDeleted + Id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(body);
  }

  public static ResponseEntity responseTaskUpdated(Long Id){
    HashMap<String, String> body = createReturnValue(responseTaskUpdated + Id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(body);
  }

  public static ResponseEntity responseTaskNotFound(Long Id){
    HashMap<String, String> body = createReturnValue(responseTaskNotFound + Id);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(body);
  }

  public static ResponseEntity responseUserNotFound(String email){
    HashMap<String, String> body = createReturnValue(responseUserNotFound + email);
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(body);
  }

  public static ResponseEntity responseConflict(String email){
    HashMap<String, String> body = createReturnValue(responseConflictEmail + email);
    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(body);
  }

  public static ResponseEntity responseTaskCreated(Long Id) {
    HashMap<String, String> body = createReturnValue(responseCreated + Id);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseUserCreated(Long Id, String token) {
    HashMap<String, String> body = createReturnValue(responseCreated + Id, token);
    System.out.println(body);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseLoginSuccess(Long Id, String token) {
    HashMap<String, String> body = createReturnValue(responseCreated + Id, token);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseInternalServerError() {
    HashMap<String, String> body = createReturnValue(responseInternalServerError);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(body);
  }

  public static ResponseEntity responseNotAuthorized(){
    HashMap<String, String> body = createReturnValue(responseNotAuthorized);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(body);
  }

}
