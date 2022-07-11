package com.example.todoappservice.core.user;

import com.example.todoappservice.server.task.Task;
import com.example.todoappservice.server.user.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoUtils {

  private final UserDao userDao;

  @Autowired
  public UserDaoUtils(UserDao userDao){
    this.userDao = userDao;
  }

  public void registerUser(User user) {
    userDao.save(user);
  }

  public boolean findUser(Long Id){
    return userDao.findById(Id).isPresent();
  }

}
