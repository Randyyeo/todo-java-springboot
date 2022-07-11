package com.example.todoappservice.core.user;

import com.example.todoappservice.server.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
  Optional<User> findStudentByEmail(String email);
}
