package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Task;
import org.example.projectmanagerapp.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Operations for managing tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a task", description = "Adds a new task to the database")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }
}