package com.example.todoappservice.server.task;

import com.example.todoappservice.core.exception.InternalServerException;
import com.example.todoappservice.core.task.TaskDaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

  public void createTask(Task task) {
    try {
      taskDaoUtils.createTask(task);
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }

  public void deleteTask(Long taskId) {
    try {
      taskDaoUtils.deleteTaskById(taskId);
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }

  @Transactional
  public void updateTask(Long taskId, String taskMessage, boolean needReminder) {
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
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }

  public Task getTaskById(Long taskId) {
    try {
      return taskDaoUtils.getTaskById(taskId);
    } catch (Exception e){
      throw new InternalServerException(e.getMessage());
    }
  }
}
