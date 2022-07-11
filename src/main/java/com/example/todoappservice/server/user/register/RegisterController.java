package com.example.todoappservice.server.user.register;

import com.example.todoappservice.core.user.UserDao;
import com.example.todoappservice.core.user.UserDaoUtils;
import com.example.todoappservice.core.utils.ResponseUtil;
import com.example.todoappservice.server.user.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/register")
public class RegisterController {

  private final RegisterService registerService;

  @Autowired
  public RegisterController(RegisterService registerService){
    this.registerService = registerService;
  }

  public ResponseEntity registerUser(@RequestBody User user){
    return registerService.registerUser(user);
  }
}
