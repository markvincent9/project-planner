package com.osea.projectplanner.service;

import com.osea.projectplanner.model.Task;
import com.osea.projectplanner.model.TaskStatus;
import com.osea.projectplanner.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @Autowired
    public SchedulerServiceImpl(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @Override
    @Transactional
    @Scheduled(fixedDelay = 10000)
    public void startOpenTask() {
        List<Task> tasks = taskRepository.findAllByStartAtIsLessThanEqualAndStatus(new Date(), TaskStatus.OPEN);

        for (Task task : tasks) {
            try {
                taskService.startTask(task);
                logger.info("Task Id {} has been started", task.getId());
            } catch (Exception e) {
                logger.warn("Error Staring Task ID {}, message: {} ", task.getId(), e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    @Scheduled(fixedDelay = 10000)
    public void completeTask() {
        List<Task> tasks = taskRepository.findAllByEndAtIsLessThanEqualAndStatus(new Date(), TaskStatus.INPROGRESS);

        for (Task task : tasks) {
            try {
                taskService.completeTask(task);
                logger.info("Task Id {} has been completed", task.getId());
            } catch (Exception e) {
                logger.warn("Error Completing Task ID {}, message: {} ", task.getId(), e.getMessage());
            }
        }
    }
}
