package com.example.TodoJWT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TodoJWT.entity.Task;
import com.example.TodoJWT.entity.User;
import com.example.TodoJWT.repository.TaskRepository;
import com.example.TodoJWT.repository.UserRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    // Get all tasks for logged-in user
    public List<Task> getTasksForUser(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(user);
    }

    // Add new task
    public Task addTask(String username, Task task) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        task.setUser(user);
        return taskRepository.save(task);
    }

    // Update task
    public Task updateTask(String username, Long taskId, Task updatedTask) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // check whether the task belongs to the user or not
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this task");
        }

        // Update fields
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());

        return taskRepository.save(task);
    }

    // Delete task
    public void deleteTask(String username, Long taskId) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Ownership check
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to delete this task");
        }

        taskRepository.delete(task);
    }
}