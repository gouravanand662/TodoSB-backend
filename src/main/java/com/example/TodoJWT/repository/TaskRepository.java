package com.example.TodoJWT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TodoJWT.entity.Task;
import com.example.TodoJWT.entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Get all tasks for a specific user
    List<Task> findByUser(User user);
}