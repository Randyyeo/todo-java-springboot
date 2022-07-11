package com.example.todoappservice.core.authentication;

import com.example.todoappservice.core.exception.UserForbiddenException;
import com.example.todoappservice.core.exception.UserUnauthorizedException;
import com.example.todoappservice.core.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    try {
      String token = request.getHeader("Authorization").split(" ")[1];
      try {
        Claims jwtValues = JwtUtil.verifyToken(token);
        request.setAttribute("userId", jwtValues.get("id"));
        return true;

      } catch (Exception e){
        throw new UserForbiddenException("User is forbidden to access");
      }

    } catch(Exception e){
      System.out.println("ERROR");
      if (e instanceof UserForbiddenException){
        throw new UserForbiddenException(e.getMessage());
      } else {
        throw new UserUnauthorizedException("User is unauthorized");
      }
    }
  }

  public boolean postHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    try {
      return true;
    } catch(Exception e){
      throw new UserUnauthorizedException("User is unauthorized");
    }
  }
}
