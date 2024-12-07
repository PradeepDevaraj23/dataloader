package com.prdtech.dataloader.model;

import com.prdtech.dataloader.enums.TASKSTATUS;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class TaskTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String taskName;
    private String taskStatus;
    private Timestamp startDate;
    private Timestamp endDate;
    private String runByUser;

    public TaskTracker() {}

    public TaskTracker(String taskName, String taskStatus, Timestamp startDate, Timestamp endDate, String runByUser) {
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.runByUser = runByUser;
    }
}
