package com.prdtech.dataloader.service;

import com.prdtech.dataloader.dto.CompanyDataDto;
import com.prdtech.dataloader.model.CompanyData;
import com.prdtech.dataloader.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class LoaderService {

    private final FileLoader fileLoader;
    private final CompanyRepository companyRepository;

    @Autowired
    public LoaderService(FileLoader fileLoader, CompanyRepository companyRepository) {
        this.fileLoader = fileLoader;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void loadData() throws IOException {
        log.info("Loading data...");
        List<String[]> data = fileLoader.readCSVFile();
        if (data == null) {
            throw new NullPointerException("Data is null");
        }
        List<CompanyData> newCompanyDataList = new ArrayList<>();
        Set<String> existingSymbols = new HashSet<>(companyRepository.findAllSymbol());

        for (String[] row : data) {
            log.info("Processing row: {}", row);
            String symbol = row[0];
            if (!existingSymbols.contains(symbol)) {
                CompanyData companyData = new CompanyData();
                companyData.setSymbol(row[0]);
                companyData.setNameOfCompany(row[1]);
                companyData.setSeries(row[2]);
                companyData.setDateOfListing(parseDate(row[3]));
                companyData.setPaidUpValue(Integer.parseInt(row[4]));
                companyData.setMarketLot(Integer.parseInt(row[5]));
                companyData.setIsinNumber(row[6]);
                companyData.setFaceValue(Integer.parseInt(row[7]));
                newCompanyDataList.add(companyData);
            } else {
                log.info("Symbol {} already exists in the database", symbol);
            }

        }
        companyRepository.saveAll(newCompanyDataList.stream().sorted(Comparator.comparing(CompanyData::getSymbol)).toList());
    }

    public LocalDate parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy",Locale.ENGLISH);
        try {
            return formatter.parse(dateString).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            log.error("Error parsing date: {} - {}", dateString, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<List<CompanyDataDto>> getCompaniesDate() {
      List<CompanyData> companyDataList =  companyRepository.findAll();
      return CompletableFuture.completedFuture(companyDataList.stream().map(this::toDto).toList());
    }

    private CompanyDataDto toDto(CompanyData companyData) {
        return new CompanyDataDto(companyData.getId(), companyData.getSymbol(), companyData.getNameOfCompany(), companyData.getSeries(), companyData.getDateOfListing(), companyData.getPaidUpValue(), companyData.getMarketLot(), companyData.getIsinNumber(), companyData.getFaceValue());
    }
}
