package com.osea.projectplanner.dto;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDto {

    @Null
    private Long id;
    private String name;
    private Date startAt;
    private Date endAt;
    private String status;

    private List<TaskDto> dependentTasks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public List<TaskDto> getDependentTasks() {
        return dependentTasks;
    }

    public void setDependentTasks(List<TaskDto> dependentTasks) {
        this.dependentTasks = dependentTasks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}