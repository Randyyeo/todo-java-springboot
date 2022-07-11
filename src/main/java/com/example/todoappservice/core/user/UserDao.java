package com.example.todoappservice.core.user;

import com.example.todoappservice.server.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
