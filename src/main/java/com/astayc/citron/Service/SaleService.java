package com.astayc.citron.Service;

import com.astayc.citron.DTO.SaleDTO;
import com.astayc.citron.Entity.Sale;

import java.util.List;

public interface SaleService {
    SaleDTO createSale(SaleDTO saleDTO);
    List<SaleDTO> getAllSales();
    SaleDTO updateSale(Long id, SaleDTO saleDTO);
    void deleteSale(Long id);
}
