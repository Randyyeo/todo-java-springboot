package com.example.todoappservice.core.user;

import com.example.todoappservice.server.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

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
    return userDao.findStudentByEmail(email).get();
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
    return new String(bytes) == enteredPassword;
  }

}
