package com.osea.projectplanner.repository;

import com.osea.projectplanner.model.Task;
import com.osea.projectplanner.model.TaskDependency;
import com.osea.projectplanner.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {
    boolean existsByTaskEqualsAndDependentTaskStatusNot(Task task, TaskStatus status);

    List<TaskDependency> findAllByTask(Task task);
}
