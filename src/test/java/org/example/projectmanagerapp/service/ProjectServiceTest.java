package org.example.projectmanagerapp.service;

import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    private ProjectRepository projectRepository;
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        projectRepository = mock(ProjectRepository.class);
        projectService = new ProjectService(projectRepository);
    }

    @Test
    @DisplayName("Should return all projects")
    void testGetAllProjects() {
        Project p1 = new Project();
        p1.setName("Project1");
        Project p2 = new Project();
        p2.setName("Project2");

        when(projectRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Project> projects = projectService.getAllProjects();

        assertEquals(2, projects.size());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should save a project")
    void testSaveProject() {
        Project project = new Project();
        project.setName("NewProject");

        when(projectRepository.save(project)).thenReturn(project);

        Project saved = projectService.saveProject(project);

        assertEquals("NewProject", saved.getName());
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    @DisplayName("Should return project by ID")
    void testGetProjectById() {
        Project project = new Project();
        project.setId(1L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        Optional<Project> result = projectService.getProjectById(1L);

        assertTrue(result.isPresent());
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should delete a project")
    void testDeleteProject() {
        projectService.deleteProject(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should update a project")
    void testUpdateProject() {
        Project project = new Project();
        project.setName("Updated");

        when(projectRepository.save(project)).thenReturn(project);

        Project updated = projectService.updateProject(1L, project);

        assertEquals("Updated", updated.getName());
        verify(projectRepository, times(1)).save(project);
    }
}