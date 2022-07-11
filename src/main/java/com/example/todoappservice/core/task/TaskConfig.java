package com.example.todoappservice.core.task;

import com.example.todoappservice.server.task.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class TaskConfig {

  @Bean
  CommandLineRunner commandLineRunner(TaskDaoUtils taskDaoUtils) {
    return args -> {
      Task mariam = new Task(
              1L,
              "Improve Coding Skills",
              LocalDate.now(),
              LocalTime.now(),
              true
      );

      taskDaoUtils.createTask(
              mariam
      );
    };
  }
}
