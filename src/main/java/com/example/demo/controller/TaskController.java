package com.example.demo.controller;

import com.example.demo.Interfaces.TaskService;
import com.example.demo.model.Task;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> taskList = taskService.getAllTasks();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getUserById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) {
//        try {
            Task updatedtask = taskService.updateTask(task);
            return ResponseEntity.ok(updatedtask);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
