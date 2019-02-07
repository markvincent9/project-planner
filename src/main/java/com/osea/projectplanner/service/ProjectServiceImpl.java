package com.osea.projectplanner.service;

import com.osea.projectplanner.dto.ProjectDto;
import com.osea.projectplanner.dto.TaskDto;
import com.osea.projectplanner.exception.ProjectNotFoundException;
import com.osea.projectplanner.model.Project;
import com.osea.projectplanner.model.Task;
import com.osea.projectplanner.model.TaskDependency;
import com.osea.projectplanner.repository.ProjectRepository;
import com.osea.projectplanner.repository.TaskDependencyRepository;
import com.osea.projectplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    private final TaskDependencyRepository taskDependencyRepository;

    @Autowired
    public ProjectServiceImpl(
            ProjectRepository projectRepository,
            TaskRepository taskRepository,
            TaskDependencyRepository taskDependencyRepository
    ) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.taskDependencyRepository = taskDependencyRepository;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapProjectToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto createProject(ProjectDto project) {
        Project newProject = new Project();
        newProject.setName(project.getName());
        projectRepository.saveAndFlush(newProject);

        project.getTasks().forEach(task -> {
            try {
                addTask(newProject.getId(), task);
            } catch (ProjectNotFoundException e) {
                e.printStackTrace();
            }
        });

        return mapProjectToDto(newProject);
    }

    @Override
    public TaskDto addTask(Long projectId, TaskDto newTask) throws ProjectNotFoundException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);

        Task parentTask = new Task(project, newTask.getName(), newTask.getStartAt(), newTask.getEndAt());
        taskRepository.saveAndFlush(parentTask);

        newTask.getDependentTasks().forEach(t -> saveDependentTasks(project, parentTask, t));

        return mapTaskToTaskDto(parentTask);
    }

    private void saveDependentTasks(Project project, Task parent, TaskDto dependent) {
        Task dependentTask = new Task(
                project, dependent.getName(), dependent.getStartAt(), dependent.getEndAt());
        taskRepository.saveAndFlush(dependentTask);

        TaskDependency taskDependency = new TaskDependency(parent, dependentTask);
        taskDependencyRepository.saveAndFlush(taskDependency);

        dependent.getDependentTasks().forEach(taskDto -> saveDependentTasks(project, dependentTask, taskDto));
    }

    private ProjectDto mapProjectToDto(Project project) {
        List<Task> parentTask = taskRepository.findAllParentTask(project.getId());

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setTasks(parentTask.stream().map(this::mapTaskToTaskDto).collect(Collectors.toList()));

        return projectDto;
    }

    private TaskDto mapTaskToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setStartAt(task.getStartAt());
        taskDto.setEndAt(task.getEndAt());
        taskDto.setStatus(task.getStatus().toString());

        List<TaskDependency> taskDependencies = taskDependencyRepository.findAllByTask(task);

        taskDto.setDependentTasks(
                taskDependencies.stream().map(t -> {
                    Task dependency = t.getDependentTask();
                    return mapTaskToTaskDto(dependency);
                }).collect(Collectors.toList())
        );

        return taskDto;
    }

}
