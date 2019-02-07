package com.osea.projectplanner.repository;

import com.osea.projectplanner.model.Task;
import com.osea.projectplanner.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByStartAtIsLessThanEqualAndStatus(Date now, TaskStatus status);

    List<Task> findAllByEndAtIsLessThanEqualAndStatus(Date now, TaskStatus status);

    @Query(value = "select * from task as t " +
            "where t.project_id = ?1 " +
            "and t.id not in (select distinct td.task_dependency_id from task_dependency as td)",nativeQuery = true)
    List<Task> findAllParentTask(Long projectId);
}
