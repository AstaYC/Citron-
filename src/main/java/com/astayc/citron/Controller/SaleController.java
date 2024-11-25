package com.astayc.citron.Controller;

import com.astayc.citron.DTO.SaleDTO;
import com.astayc.citron.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDTO createSale(@RequestBody SaleDTO saleDTO) {
        return saleService.createSale(saleDTO);
    }

    @GetMapping
    public List<SaleDTO> getAllSales() {
        return saleService.getAllSales();
    }

    @PutMapping("/{id}")
    public SaleDTO updateSale(@PathVariable Long id, @RequestBody SaleDTO saleDTO) {
        return saleService.updateSale(id, saleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
    }

}
