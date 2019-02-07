package com.osea.projectplanner.service;

import com.osea.projectplanner.exception.SubtaskIncompleteException;
import com.osea.projectplanner.exception.TaskNotFoundException;
import com.osea.projectplanner.model.Task;
import com.osea.projectplanner.model.TaskStatus;
import com.osea.projectplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskDependencyService taskDependencyService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskDependencyService taskDependencyService) {
        this.taskRepository = taskRepository;
        this.taskDependencyService = taskDependencyService;
    }

    @Override
    @Transactional
    public Task startTask(Long taskId) throws TaskNotFoundException, SubtaskIncompleteException {
        Task task = getTask(taskId);

        return startTask(task);
    }

    @Override
    @Transactional
    public Task startTask(Task task) throws SubtaskIncompleteException {
        checkIncompleteTask(task);
        task.setStatus(TaskStatus.INPROGRESS);

        return task;
    }

    @Override
    @Transactional
    public Task completeTask(Task task) throws SubtaskIncompleteException {
        checkIncompleteTask(task);
        task.setStatus(TaskStatus.COMPLETE);

        return task;
    }

    private Task getTask(Long taskId) throws TaskNotFoundException {
        return taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    private void checkIncompleteTask(Task task) throws SubtaskIncompleteException {
        if (taskDependencyService.hasIncompleteTask(task)) {
            throw new SubtaskIncompleteException();
        }
    }
}
