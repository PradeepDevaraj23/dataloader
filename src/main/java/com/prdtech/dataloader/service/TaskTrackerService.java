package com.prdtech.dataloader.service;

import com.prdtech.dataloader.dto.TaskTrackerDTO;
import com.prdtech.dataloader.enums.TASKSTATUS;
import com.prdtech.dataloader.model.TaskTracker;
import com.prdtech.dataloader.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class TaskTrackerService {


private final TaskRepository taskRepository;

    public TaskTrackerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public CompletableFuture<TaskTracker> createTask(TaskTracker taskTracker) {
        log.info("Creating task with name {}", taskTracker);
        return CompletableFuture.completedFuture(taskRepository.save(taskTracker));
    }

    public void updateTask(Long id,TASKSTATUS taskStatus) {
        taskRepository.findById(id).ifPresent(t -> {
            log.info("Updating task with id {} with status {}", id, taskStatus);
            t.setTaskStatus(taskStatus.name());
            t.setEndDate(new Timestamp(Instant.now().toEpochMilli()));
            CompletableFuture.completedFuture(taskRepository.save(t));
        });
    }

    public List<TaskTrackerDTO> getTaskDetails() {
        return taskRepository.findAll().stream().map(this::toDto).toList();
    }

    private TaskTrackerDTO toDto(TaskTracker taskTracker) {
        TaskTrackerDTO taskTrackerDTO = new TaskTrackerDTO(taskTracker.getId(), taskTracker.getTaskName(), TASKSTATUS.valueOf(taskTracker.getTaskStatus()), taskTracker.getStartDate(), taskTracker.getEndDate(), taskTracker.getRunByUser());
        return taskTrackerDTO;
    }
}
