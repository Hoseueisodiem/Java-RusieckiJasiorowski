package org.example.projectmanagerapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Operations for managing projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    @Operation(summary = "Get all projects", description = "Returns a list of all projects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a project", description = "Adds a new project to the database")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }
}