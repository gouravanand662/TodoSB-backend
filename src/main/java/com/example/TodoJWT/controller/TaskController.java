package com.example.TodoJWT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TodoJWT.entity.Task;
import com.example.TodoJWT.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Get all tasks for logged-in user
    @GetMapping
    public List<Task> getTasks(Authentication authentication) {
        String username = authentication.getName();
        return taskService.getTasksForUser(username);
    }

    // Add new task
    @PostMapping
    public Task addTask(@RequestBody Task task, Authentication authentication) {
        String username = authentication.getName();
        return taskService.addTask(username, task);
    }

    // Update task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task updatedTask,
                           Authentication authentication) {

        String username = authentication.getName();
        return taskService.updateTask(username, id, updatedTask);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id,
                            Authentication authentication) {

        String username = authentication.getName();
        taskService.deleteTask(username, id);

        return "Task deleted successfully";
    }
}