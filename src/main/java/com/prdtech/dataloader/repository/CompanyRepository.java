package com.prdtech.dataloader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<com.prdtech.dataloader.model.CompanyData, Long> {

    @Query("SELECT c.symbol FROM CompanyData c")
    List<String> findAllSymbol();
}
