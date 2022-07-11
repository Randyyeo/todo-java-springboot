package com.example.todoappservice.core.user;

import com.example.todoappservice.core.exception.TaskNotFoundException;
import com.example.todoappservice.server.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Objects;

@Component
public class UserDaoUtils {

  private final UserDao userDao;

  @Autowired
  public UserDaoUtils(UserDao userDao){
    this.userDao = userDao;
  }

  public void registerUser(User user) {
    userDao.save(user);
  }

  public User findUser(String email){
    try {
      return userDao.findStudentByEmail(email).get();
    } catch (Exception e) {
      throw new TaskNotFoundException("User cannot be found for email: " + email);
    }
  }

  public boolean isUserPresent(String email){
      return userDao.findStudentByEmail(email).isPresent();

  }

  public void hashPassword(User user, String password){
    Base64.Encoder encoder = Base64.getEncoder();
    user.setPassword(encoder.encodeToString(password.getBytes()));
  }

  public boolean verifyPassword(String encryptedPassword, String enteredPassword){
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] bytes = decoder.decode(encryptedPassword);
    return Objects.equals(new String(bytes), enteredPassword);
  }
}
