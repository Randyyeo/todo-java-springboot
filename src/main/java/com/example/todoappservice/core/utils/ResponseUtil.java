package com.example.todoappservice.core.utils;

import com.example.todoappservice.server.task.Task;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class ResponseUtil {

  private final static String responseConflict = "User already exists for Id ";
  private final static String responseCreated = "User has been created for Id ";
  private final static String responseInternalServerError = "Internal Server Error has occured";

  public static JSONObject createReturnValue(String message){
    return new JSONObject()
            .put("message", message);
  }

  public static JSONObject createReturnValue(String message, String token){
    return new JSONObject()
            .put("message", message)
            .put("token", token);
  }
  public static ResponseEntity<Task> responseOk(Task task){
    return ResponseEntity.status(HttpStatus.OK)
            .body(task);
  }

  public static ResponseEntity<Exception> responseNotFound(Exception e){
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e);
  }

  public static ResponseEntity responseConflict(Long Id){
    JSONObject body = createReturnValue(responseConflict + Id);
    return ResponseEntity.status(HttpStatus.CONFLICT)
            .body(body);
  }

  public static ResponseEntity responseTaskCreated(Long Id) {
    JSONObject body = createReturnValue(responseCreated + Id);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseUserCreated(Long Id, String token) {
    JSONObject body = createReturnValue(responseCreated + Id, token);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(body);
  }

  public static ResponseEntity responseInternalServerError() {
    JSONObject body = createReturnValue(responseInternalServerError);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(body);
  }

}
