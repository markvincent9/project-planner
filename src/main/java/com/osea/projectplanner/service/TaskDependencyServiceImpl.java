package com.osea.projectplanner.service;

import com.osea.projectplanner.model.Task;
import com.osea.projectplanner.model.TaskStatus;
import com.osea.projectplanner.repository.TaskDependencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskDependencyServiceImpl implements TaskDependencyService {

    private final TaskDependencyRepository taskDependencyRepository;

    @Autowired
    public TaskDependencyServiceImpl(TaskDependencyRepository taskDependencyRepository) {
        this.taskDependencyRepository = taskDependencyRepository;
    }

    @Override
    public boolean hasIncompleteTask(Task task) {
        return taskDependencyRepository.existsByTaskEqualsAndDependentTaskStatusNot(task, TaskStatus.COMPLETE);
    }
}
