package com.astayc.citron.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDTO {

    private Long id;
    private LocalDate saleDate;
    private double unitPrice;
    private double revenue;
    private String clientName;
    private Long harvestId; // Reference to the associated Harvest
}
