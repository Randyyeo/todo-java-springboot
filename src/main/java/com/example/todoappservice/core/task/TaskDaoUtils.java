package com.example.todoappservice.core.task;

import com.example.todoappservice.core.exception.TaskNotFoundException;
import com.example.todoappservice.server.task.Task;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;

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
    try {
      return taskDao.findById(Id).get();
    } catch (Exception e){
      if (e instanceof CannotCreateTransactionException){
        System.out.println(e);
        throw e;
      } else {
        throw new TaskNotFoundException("Task cannot be found for Id: " + Id);
      }
    }
  }

  public void deleteTaskById(Long Id) {
    try {
      taskDao.deleteById(Id);
    } catch (Exception e){
      if (e instanceof JDBCConnectionException){
        throw e;
      }
      throw new TaskNotFoundException("Task cannot be found for Id: " + Id);
    }
  }

  public void createTask(Task task) {
    taskDao.save(task);
  }
}
