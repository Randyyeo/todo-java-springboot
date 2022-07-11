package com.example.todoappservice.core.task;

import com.example.todoappservice.server.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class TaskDaoUtils {

  private final TaskDao taskDao;

  @Autowired
  public TaskDaoUtils(TaskDao taskDao){
    this.taskDao = taskDao;
  }

  public List<Task> getAllTasks(){
    return taskDao.findAll();
//    return List.of(new Task(
//            1L,
//            "Improve Coding Skills",
//            LocalDate.now(),
//            LocalTime.now(),
//            true
//    ));
  }

  public Task getTaskById(Long Id){
    return taskDao.findById(Id).get();
  }

  public void deleteTaskById(Long Id) {
    taskDao.deleteById(Id);
  }

  public void createTask(Task task) {
    taskDao.save(task);
  }
}
