package com.prdtech.dataloader.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
@Slf4j
public class FileLoader {

    @Value("${csv.file.location}")
    private String csvFileLocation;

    public List<String[]> readCSVFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(csvFileLocation);
        log.info("Reading CSV file from location {}", resource.getFile().getAbsolutePath());
        try (Reader reader = Files.newBufferedReader(Paths.get(resource.getFile().getAbsolutePath()));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            log.info("CSV file found at the location {} and reading... ", csvFileLocation);
            return csvReader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

}

