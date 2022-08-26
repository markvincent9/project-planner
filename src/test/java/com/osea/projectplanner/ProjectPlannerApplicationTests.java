package com.osea.projectplanner;

import static org.assertj.core.api.Assertions.assertThat;

import com.osea.projectplanner.controller.ProjectPlannerController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectPlannerApplicationTests {

    @Autowired
    private ProjectPlannerController controller;
    
    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
