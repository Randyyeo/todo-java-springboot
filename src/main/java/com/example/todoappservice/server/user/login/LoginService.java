package com.example.todoappservice.server.user.login;

import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.exception.TaskNotFoundException;
import com.example.todoappservice.core.user.UserDaoUtils;
import com.example.todoappservice.core.utils.JwtUtil;
import com.example.todoappservice.core.utils.ResponseUtil;
import com.example.todoappservice.server.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private final UserDaoUtils userDaoUtils;
  public LoginService(UserDaoUtils userDaoUtils){
    this.userDaoUtils = userDaoUtils;
  }

  public ResponseEntity loginUser(User user){
    try {
      User userFromDatabase = userDaoUtils.findUser(user.getEmail());
      if (userFromDatabase != null){
        if (userDaoUtils.verifyPassword(userFromDatabase.getPassword(), user.getPassword())){
          String token = JwtUtil.jwtBuilder(userFromDatabase.getId(), user.getEmail(), user.getName());
          return ResponseUtil.responseLoginSuccess(userFromDatabase.getId(), token);
        } else {
          return ResponseUtil.responseNotAuthorized();
        }

      } else {
        return ResponseUtil.responseUserNotFound(user.getEmail());
      }
    } catch (Exception e){
      if (e instanceof TaskNotFoundException){
        throw new TaskNotFoundException(e.getMessage());
      } else {
        throw new InternalServerException(e.getMessage());
      }
    }

  }
}
