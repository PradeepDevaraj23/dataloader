package com.prdtech.dataloader.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class CompanyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String symbol;
    private String nameOfCompany;
    private String series;
    private LocalDate dateOfListing;
    private Integer paidUpValue;
    private Integer marketLot;
    private String isinNumber;
    private Integer faceValue;
}
