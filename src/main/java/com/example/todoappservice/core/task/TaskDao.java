package com.example.todoappservice.core.task;

import com.example.todoappservice.server.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Long> {

}
