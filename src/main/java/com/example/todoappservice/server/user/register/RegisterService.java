package com.example.todoappservice.server.user.register;

import com.example.todoappservice.core.exception.ConflictUserException;
import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.user.UserDaoUtils;
import com.example.todoappservice.core.utils.JwtUtil;
import com.example.todoappservice.core.utils.ResponseUtil;
import com.example.todoappservice.server.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class RegisterService {
  private final UserDaoUtils userDaoUtils;

  @Autowired
  public RegisterService(UserDaoUtils userDaoUtils){
    this.userDaoUtils = userDaoUtils;
  }

  public ResponseEntity registerUser(User user){
    try {
      if (userDaoUtils.findUser(user.getId())){
        return ResponseUtil.responseConflict(user.getId());
      } else {
        try {
          userDaoUtils.registerUser(user);
          String token = JwtUtil.jwtBuilder(user.getId(), user.getEmail(), user.getName());
          return ResponseUtil.responseTaskCreated(user.getId());
        } catch (Exception e){
          throw new InternalServerException(e.getMessage());
        }
      }
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }
}
