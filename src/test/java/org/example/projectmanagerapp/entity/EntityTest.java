package org.example.projectmanagerapp.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    @DisplayName("Test Users entity")
    void testUsers() {
        Users user = new Users();
        user.setId(1L);
        user.setUsername("test");

        assertEquals(1L, user.getId());
        assertEquals("test", user.getUsername());
    }

    @Test
    @DisplayName("Test Project entity")
    void testProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("MyProject");
        Set<Users> users = new HashSet<>();
        project.setUsers(users);

        assertEquals(1L, project.getId());
        assertEquals("MyProject", project.getName());
        assertNotNull(project.getUsers());
    }

    @Test
    @DisplayName("Test Task entity")
    void testTask() {
        Project project = new Project();
        Users user = new Users();

        Task task = new Task();
        task.setId(1L);
        task.setTitle("MyTask");
        task.setDescription("Description");
        task.setTaskType(TaskType.HIGH_PRIORITY);
        task.setProject(project);
        task.setUser(user);

        assertEquals(1L, task.getId());
        assertEquals("MyTask", task.getTitle());
        assertEquals("Description", task.getDescription());
        assertEquals(TaskType.HIGH_PRIORITY, task.getTaskType());
        assertNotNull(task.getProject());
        assertNotNull(task.getUser());
    }

    @Test
    @DisplayName("Test TaskType enum")
    void testTaskType() {
        assertEquals(3, TaskType.values().length);
        assertEquals(TaskType.LOW_PRIORITY, TaskType.valueOf("LOW_PRIORITY"));
    }
}