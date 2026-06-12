package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Operations for managing projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @Operation(summary = "Get all projects", description = "Returns a list of all projects")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    @Operation(summary = "Create a project", description = "Adds a new project to the database")
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a project", description = "Updates an existing project by ID")
    public Project updateProject(
            @Parameter(description = "ID of the project to update") @PathVariable Long id,
            @RequestBody Project project) {
        return projectService.updateProject(id, project);
    }

    @PostMapping("/{projectId}/users")
    @Operation(summary = "Add user to project", description = "Assigns an existing user to an existing project")
    public Project addUserToProject(
            @Parameter(description = "ID of the project") @PathVariable Long projectId,
            @Parameter(description = "ID of the user") @RequestParam Long userId) {
        return projectService.addUserToProject(projectId, userId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a project", description = "Deletes a project by ID")
    public void deleteProject(
            @Parameter(description = "ID of the project to delete") @PathVariable Long id) {
        projectService.deleteProject(id);
    }
}