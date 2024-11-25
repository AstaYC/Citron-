package com.astayc.citron.Controller;

import com.astayc.citron.DTO.SaleDTO;
import com.astayc.citron.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
