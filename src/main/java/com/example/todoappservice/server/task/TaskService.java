package com.example.todoappservice.server.task;

import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.exception.TaskNotFoundException;
import com.example.todoappservice.core.task.TaskDaoUtils;
import com.example.todoappservice.core.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService {

  private final TaskDaoUtils taskDaoUtils;

  @Autowired
  public TaskService(TaskDaoUtils taskDaoUtils) {
    this.taskDaoUtils = taskDaoUtils;
  }

  public List<Task> getAllTasks() {
    try {
      return taskDaoUtils.getAllTasks();
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }

  public ResponseEntity createTask(Task task) {
    try {
      taskDaoUtils.createTask(task);
      return ResponseUtil.responseTaskCreated(task.getId());
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }

  public ResponseEntity deleteTask(Long taskId) {
    try {
      taskDaoUtils.deleteTaskById(taskId);
      return ResponseUtil.responseTaskDeleted(taskId);
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }

  @Transactional
  public ResponseEntity updateTask(Long taskId, String taskMessage, boolean needReminder) {
    try {
      Task task = taskDaoUtils.getTaskById(taskId);

      if (taskMessage != null &&
          taskMessage.length() > 0 &&
          !Objects.equals(taskMessage, task.getTaskMessage())
      ){
        task.setTaskMessage(taskMessage);
      }

      if (needReminder != task.isReminder()
      ) {
        task.setReminder(needReminder);
      }

      return ResponseUtil.responseTaskUpdated(taskId);
    } catch (Exception e){
      if (e instanceof TaskNotFoundException){
        throw new TaskNotFoundException(e.getMessage());
      } else {
        throw new InternalServerException(e.getMessage());
      }
    }
  }

  public ResponseEntity getTaskById(Long taskId) {
    try {
      Task task = taskDaoUtils.getTaskById(taskId);
      return ResponseUtil.responseOk(task);
    } catch (Exception e){
      if (e instanceof TaskNotFoundException){
        throw new TaskNotFoundException(e.getMessage());
      } else {
        throw new InternalServerException(e.getMessage());
      }
    }
  }
}
