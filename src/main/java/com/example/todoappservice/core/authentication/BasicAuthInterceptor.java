package com.example.todoappservice.core.authentication;

import com.example.todoappservice.core.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicAuthInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    try {
      String token = request.getHeader("Authorization").split(" ")[1];
      Claims jwtValues = JwtUtil.verifyToken(token);
      if (JwtUtil.verifyToken(token) != null){
        request.setAttribute("userId", jwtValues.get("id"));
        return true;
      } else {
        response.sendError(Integer.parseInt(HttpStatus.UNAUTHORIZED.toString()), "Unauthorized User");
        return false;
      }

    } catch(Exception e){
      response.sendError(Integer.parseInt(HttpStatus.FORBIDDEN.toString()), "JWT token expired");
      return false;
    }
  }
}
