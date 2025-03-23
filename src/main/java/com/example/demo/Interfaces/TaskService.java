package com.example.demo.Interfaces;

import com.example.demo.model.Task;
import com.example.demo.model.User;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTasks();

    Task updateTask(Task task);

    void deleteTask(Long id);


}
