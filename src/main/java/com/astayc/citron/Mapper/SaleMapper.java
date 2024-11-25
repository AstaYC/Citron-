package com.astayc.citron.Mapper;

import com.astayc.citron.DTO.SaleDTO;
import com.astayc.citron.Entity.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {

    // Convert Sale to SaleDTO
    public SaleDTO toDTO(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setSaleDate(sale.getSaleDate());
        saleDTO.setUnitPrice(sale.getUnitPrice());
        saleDTO.setRevenue(sale.getRevenue());
        saleDTO.setClientName(sale.getClientName());
        return saleDTO;
    }

    // Convert SaleDTO to Sale
    public Sale toEntity(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSaleDate(saleDTO.getSaleDate());
        sale.setUnitPrice(saleDTO.getUnitPrice());
        sale.setRevenue(saleDTO.getRevenue());
        sale.setClientName(saleDTO.getClientName());
        return sale;
    }
}
