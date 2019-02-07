package com.osea.projectplanner.service;

import com.osea.projectplanner.model.Task;

public interface TaskDependencyService {
    boolean hasIncompleteTask(Task task);
}
