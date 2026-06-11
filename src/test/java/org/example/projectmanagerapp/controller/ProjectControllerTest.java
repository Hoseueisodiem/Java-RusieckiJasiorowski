package org.example.projectmanagerapp.controller;

import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectControllerTest {

    private ProjectService projectService;
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        projectService = mock(ProjectService.class);
        projectController = new ProjectController(projectService);
    }

    @Test
    @DisplayName("Should return all projects")
    void testGetAllProjects() {
        Project p1 = new Project();
        Project p2 = new Project();
        when(projectService.getAllProjects()).thenReturn(Arrays.asList(p1, p2));

        List<Project> projects = projectController.getAllProjects();

        assertEquals(2, projects.size());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    @DisplayName("Should create a project")
    void testCreateProject() {
        Project project = new Project();
        project.setName("Test");
        when(projectService.saveProject(project)).thenReturn(project);

        Project created = projectController.createProject(project);

        assertEquals("Test", created.getName());
        verify(projectService, times(1)).saveProject(project);
    }

    @Test
    @DisplayName("Should update a project")
    void testUpdateProject() {
        Project project = new Project();
        when(projectService.updateProject(1L, project)).thenReturn(project);

        Project updated = projectController.updateProject(1L, project);

        assertNotNull(updated);
        verify(projectService, times(1)).updateProject(1L, project);
    }

    @Test
    @DisplayName("Should delete a project")
    void testDeleteProject() {
        projectController.deleteProject(1L);
        verify(projectService, times(1)).deleteProject(1L);
    }
}