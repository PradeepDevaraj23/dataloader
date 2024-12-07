package com.prdtech.dataloader.dto;

import java.time.LocalDate;

public record CompanyDataDto(Long id,
                             String symbol,
                             String nameOfCompany,
                             String series,
                             LocalDate dateOfListing,
                             Integer paidUpValue,
                             Integer marketLot,
                             String isinNumber,
                             Integer faceValue) {

}
