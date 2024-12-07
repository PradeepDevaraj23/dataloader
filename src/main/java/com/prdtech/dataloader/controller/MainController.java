package com.prdtech.dataloader.controller;

import com.prdtech.dataloader.dto.CompanyDataDto;
import com.prdtech.dataloader.dto.TaskTrackerDTO;
import com.prdtech.dataloader.service.LoaderService;
import com.prdtech.dataloader.service.TaskTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class MainController {

    private final LoaderService loaderService;
    private final TaskTrackerService taskTrackerService;

    @Autowired
    public MainController(LoaderService loaderService, LoaderService loaderService1, TaskTrackerService taskTrackerService) {
        this.loaderService = loaderService1;
        this.taskTrackerService = taskTrackerService;
    }

    @GetMapping("/load")
    public void loadData() throws IOException {
        try {
            loaderService.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getCompaniesDate")
    public ResponseEntity<List<CompanyDataDto>> getCompaniesDate() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(loaderService.getCompaniesDate().get());
    }

    @GetMapping("/getTaskDetails")
    public ResponseEntity<List<TaskTrackerDTO>> getTaskDetails() {
        return ResponseEntity.ok(taskTrackerService.getTaskDetails());
    }

}
