package com.prdtech.dataloader.repository;

import com.prdtech.dataloader.model.TaskTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskTracker, Long> {

}
