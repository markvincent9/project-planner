package com.osea.projectplanner.service;

import com.osea.projectplanner.dto.ProjectDto;
import com.osea.projectplanner.dto.TaskDto;
import com.osea.projectplanner.exception.ProjectNotFoundException;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects();
    ProjectDto createProject(ProjectDto project);
    TaskDto addTask(Long projectId, TaskDto newTask) throws ProjectNotFoundException;
}
