package com.osea.projectplanner.model;

import javax.persistence.*;

@Entity
@Table(name = "task_dependency")
public class TaskDependency extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_dependency_id", nullable = false)
    private Task dependentTask;

    public TaskDependency() {
    }

    public TaskDependency(Task task, Task dependentTask) {
        this.task = task;
        this.dependentTask = dependentTask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getDependentTask() {
        return dependentTask;
    }

    public void setDependentTask(Task dependentTask) {
        this.dependentTask = dependentTask;
    }
}
