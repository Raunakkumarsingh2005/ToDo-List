package com.example.demo.service;

import com.example.demo.Interfaces.TaskService;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        Task existingTask = getTaskById(task.getTaskId());
        if (existingTask != null) {
            existingTask.setStatus(task.getStatus());
            existingTask.setTitle(task.getTitle());
            existingTask.setDescription(task.getDescription());
            existingTask.setUser(task.getUser());
        }
        assert existingTask != null : "Task with provided taskId does not exists in database";
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task existingTask = getTaskById(id);
        if (existingTask != null) {
            taskRepository.deleteById(id);
        }
        assert existingTask != null : "Task does not exists";
    }

//    public Task createTask(Task task) {
//        return taskRepository.save(task);
//    }
//
//    public Task getTask(Long id) {
//        return taskRepository.findById(id).orElse(null);
//    }
//
//    public Task updateTask(Task task) {
//
//    }
//
//    public void deleteTask(Long id) {
//        taskRepository.deleteById(id);
//    }

}
