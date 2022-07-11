package com.example.todoappservice.server.task;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Task {

    @javax.persistence.Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long Id;
    private String taskMessage;
    private LocalDate date;
    private LocalTime time;
    private boolean needReminder;

    public Task(){

    }

    public Task(
            Long Id,
            String taskMessage,
            LocalDate date,
            LocalTime time,
            boolean needReminder
    ){
        this.Id = Id;
        this.taskMessage = taskMessage;
        this.date = date;
        this.time = time;
        this.needReminder = needReminder;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isReminder() {
        return needReminder;
    }

    public void setReminder(boolean reminder) {
        this.needReminder = reminder;
    }
}
