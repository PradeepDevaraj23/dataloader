package com.prdtech.dataloader.controller;

import com.prdtech.dataloader.dto.CompanyDataDto;
import com.prdtech.dataloader.service.LoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
public class LoaderController {

    private final LoaderService loaderService;

    @Autowired
    public LoaderController(LoaderService loaderService, LoaderService loaderService1) {
        this.loaderService = loaderService1;
    }

    @GetMapping("/load")
    public void loadData() throws IOException {
        loaderService.loadData();
    }

    @GetMapping("/getCompaniesDate")
    public ResponseEntity<CompletableFuture<List<CompanyDataDto>>> getCompaniesDate() throws IOException {
        return ResponseEntity.ok(loaderService.getCompaniesDate());
    }

}
