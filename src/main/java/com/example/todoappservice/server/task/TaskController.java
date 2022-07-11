package com.example.todoappservice.server.task;

import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/task")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public List<Task> getAllTasks() {

    return taskService.getAllTasks();

  }

  @GetMapping(path = "{taskId}")
  public ResponseEntity getTaskById(@PathVariable("taskId") Long taskId) {
    return taskService.getTaskById(taskId);
  }

  @PostMapping
  public ResponseEntity createTask(@RequestBody Task task) {
    return taskService.createTask(task);
  }

  @DeleteMapping(path = "{taskId}")
  public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId) {
    return taskService.deleteTask(taskId);
  }

  @PutMapping(path = "{taskId}")
  public ResponseEntity updateTask(
          @PathVariable("studentId") Long taskId,
          @RequestParam(required = false) String task,
          @RequestParam(required = false) boolean needReminder
  ) {
    return taskService.updateTask(taskId, task, needReminder);
  }
}
