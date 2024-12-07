package com.prdtech.dataloader.dto;

import com.prdtech.dataloader.enums.TASKSTATUS;

import java.sql.Timestamp;

public record TaskTrackerDTO( Long id,
         String taskName,
         TASKSTATUS taskStatus,
         Timestamp startDate,
         Timestamp endDate,
         String runByUser) {
}
