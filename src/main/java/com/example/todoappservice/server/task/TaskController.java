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
    public List<Task> getAllTasks(){
        try {
            return taskService.getAllTasks();
        } catch (Exception e){
            throw new InternalServerException(e.getMessage());
        }
    }

    @GetMapping(path = "{taskId}")
    public ResponseEntity getTaskById(@PathVariable("taskId") Long taskId){
        try {
            Task task = taskService.getTaskById(taskId);
            return ResponseUtil.responseOk(task);
        } catch (Exception e) {
            return ResponseUtil.responseNotFound(e);
        }
    }

    @PostMapping
    public void createTask(@RequestBody Task task){
        taskService.createTask(task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId){
        taskService.deleteTask(taskId);
    }

    @PutMapping(path = "{taskId}")
    public void updateTask(
            @PathVariable("studentId") Long taskId,
            @RequestParam(required = false) String task,
            @RequestParam(required = false) boolean needReminder
    ){
        taskService.updateTask(taskId, task, needReminder);
    }
}
