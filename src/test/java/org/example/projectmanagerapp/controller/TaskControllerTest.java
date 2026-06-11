package org.example.projectmanagerapp.controller;

import org.example.projectmanagerapp.entity.Task;
import org.example.projectmanagerapp.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        taskService = mock(TaskService.class);
        taskController = new TaskController(taskService);
    }

    @Test
    @DisplayName("Should return all tasks")
    void testGetAllTasks() {
        Task t1 = new Task();
        Task t2 = new Task();
        when(taskService.getAllTasks()).thenReturn(Arrays.asList(t1, t2));

        List<Task> tasks = taskController.getAllTasks();

        assertEquals(2, tasks.size());
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    @DisplayName("Should create a task")
    void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test");
        when(taskService.saveTask(task)).thenReturn(task);

        Task created = taskController.createTask(task);

        assertEquals("Test", created.getTitle());
        verify(taskService, times(1)).saveTask(task);
    }

    @Test
    @DisplayName("Should update a task")
    void testUpdateTask() {
        Task task = new Task();
        when(taskService.updateTask(1L, task)).thenReturn(task);

        Task updated = taskController.updateTask(1L, task);

        assertNotNull(updated);
        verify(taskService, times(1)).updateTask(1L, task);
    }

    @Test
    @DisplayName("Should delete a task")
    void testDeleteTask() {
        taskController.deleteTask(1L);
        verify(taskService, times(1)).deleteTask(1L);
    }
}