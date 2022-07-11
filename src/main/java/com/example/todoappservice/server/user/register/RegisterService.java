package com.example.todoappservice.server.user.register;

import com.example.todoappservice.core.exception.ConflictUserException;
import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.user.UserDaoUtils;
import com.example.todoappservice.core.utils.JwtUtil;
import com.example.todoappservice.core.utils.ResponseUtil;
import com.example.todoappservice.server.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Service
public class RegisterService {
  private final UserDaoUtils userDaoUtils;

  @Autowired
  public RegisterService(UserDaoUtils userDaoUtils){
    this.userDaoUtils = userDaoUtils;
  }

  public ResponseEntity registerUser(User user){
    System.out.println(user.getName());
    System.out.println(user.getName());
    System.out.println(user.getName());

    try {
      if (userDaoUtils.isUserPresent(user.getEmail())){
        return ResponseUtil.responseConflict(user.getEmail());
      } else {
        try {
          String token = JwtUtil.jwtBuilder(user.getId(), user.getEmail(), user.getName());
          userDaoUtils.hashPassword(user, user.getPassword());
          userDaoUtils.registerUser(user);
          User newUser = userDaoUtils.findUser(user.getEmail());
          return ResponseUtil.responseUserCreated(newUser.getId(), token);
        } catch (Exception e){
          throw new InternalServerException(e.getMessage());
        }
      }
    } catch (Exception e){
      System.out.println(e);
      throw new InternalServerException(e.getMessage());
    }
  }
}
