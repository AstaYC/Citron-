package com.astayc.citron.Service.Impl;

import com.astayc.citron.DTO.SaleDTO;
import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Entity.Sale;
import com.astayc.citron.Mapper.SaleMapper;
import com.astayc.citron.Repository.HarvestRepository;
import com.astayc.citron.Repository.SaleRepository;
import com.astayc.citron.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private HarvestRepository harvestRepository;

    @Autowired
    private SaleMapper saleMapper;

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

    // Get All Sales
    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(saleMapper::toDTO) // Use mapper
                .collect(Collectors.toList());
    }

    // Update Sale
    @Override
    public SaleDTO updateSale(Long id, SaleDTO saleDTO) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id " + id));

        // Update fields
        existingSale.setSaleDate(saleDTO.getSaleDate());
        existingSale.setUnitPrice(saleDTO.getUnitPrice());
        existingSale.setRevenue(saleDTO.getRevenue());
        existingSale.setClientName(saleDTO.getClientName());

        Sale updatedSale = saleRepository.save(existingSale);
        return saleMapper.toDTO(updatedSale); // Use mapper
    }

    // Delete Sale
    @Override
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found with id " + id));
        saleRepository.delete(sale);
    }
}
