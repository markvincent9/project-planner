package com.osea.projectplanner.service;

import com.osea.projectplanner.exception.SubtaskIncompleteException;
import com.osea.projectplanner.exception.TaskNotFoundException;
import com.osea.projectplanner.model.Task;

public interface TaskService {
    Task startTask(Long taskId) throws TaskNotFoundException, SubtaskIncompleteException;
    Task startTask(Task task) throws SubtaskIncompleteException;

    Task completeTask(Task task) throws SubtaskIncompleteException;
}
