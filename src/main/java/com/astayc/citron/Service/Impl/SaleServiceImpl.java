package com.astayc.citron.Service.Impl;

import com.astayc.citron.DTO.SaleDTO;
import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Entity.Sale;
import com.astayc.citron.Repository.HarvestRepository;
import com.astayc.citron.Repository.SaleRepository;
import com.astayc.citron.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    public SaleDTO createSale(SaleDTO saleDTO) {
        Harvest harvest = harvestRepository.findById(saleDTO.getHarvestId())
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found"));

        if (harvest.getTotalQuantity() == 0) {
            throw new IllegalArgumentException("Cannot create sale for harvest with zero quantity");
        }

        // Calculate revenue and update Harvest quantity
        double revenue = harvest.getTotalQuantity() * saleDTO.getUnitPrice();
        harvest.setTotalQuantity(0);
        harvestRepository.save(harvest);

        // Create the Sale entity
        Sale sale = Sale.builder()
                .saleDate(LocalDate.now())
                .unitPrice(saleDTO.getUnitPrice())
                .revenue(revenue)
                .clientName(saleDTO.getClientName())
                .harvest(harvest)
                .build();

        Sale savedSale = saleRepository.save(sale);

        // Return a DTO representation
        saleDTO.setId(savedSale.getId());
        saleDTO.setRevenue(revenue);
        return saleDTO;
    }
}
