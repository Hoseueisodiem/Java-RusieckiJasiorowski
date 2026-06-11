package org.example.projectmanagerapp.service;

import org.example.projectmanagerapp.entity.Task;
import org.example.projectmanagerapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    @DisplayName("Should return all tasks")
    void testGetAllTasks() {
        Task t1 = new Task();
        t1.setTitle("Task1");
        Task t2 = new Task();
        t2.setTitle("Task2");

        when(taskRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Task> tasks = taskService.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should save a task")
    void testSaveTask() {
        Task task = new Task();
        task.setTitle("NewTask");

        when(taskRepository.save(task)).thenReturn(task);

        Task saved = taskService.saveTask(task);

        assertEquals("NewTask", saved.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    @DisplayName("Should return task by ID")
    void testGetTaskById() {
        Task task = new Task();
        task.setId(1L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should delete a task")
    void testDeleteTask() {
        taskService.deleteTask(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should update a task")
    void testUpdateTask() {
        Task task = new Task();
        task.setTitle("Updated");

        when(taskRepository.save(task)).thenReturn(task);

        Task updated = taskService.updateTask(1L, task);

        assertEquals("Updated", updated.getTitle());
        verify(taskRepository, times(1)).save(task);
    }
}