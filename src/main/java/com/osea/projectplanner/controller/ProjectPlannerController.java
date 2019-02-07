package com.osea.projectplanner.controller;

import com.osea.projectplanner.dto.ProjectDto;
import com.osea.projectplanner.dto.TaskDto;
import com.osea.projectplanner.exception.ProjectNotFoundException;
import com.osea.projectplanner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectPlannerController {

    private final ProjectService projectService;

    @Autowired
    public ProjectPlannerController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("/projects")
    public ProjectDto createProject(@RequestBody ProjectDto newProject) {
        return projectService.createProject(newProject);
    }

    @PostMapping("/tasks")
    public TaskDto addTask(
            @RequestBody TaskDto newTask,
            @RequestParam Long projectId
    ) throws ProjectNotFoundException {
        return projectService.addTask(projectId, newTask);
    }
}
