package com.osea.projectplanner.dto;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

public class ProjectDto {

    @Null
    private Long id;

    private String name;

    private List<TaskDto> tasks = new ArrayList<>();

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

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
