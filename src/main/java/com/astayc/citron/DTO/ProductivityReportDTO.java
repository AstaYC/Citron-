package com.astayc.citron.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductivityReportDTO {
    private String fieldName;
    private String season;
    private double totalQuantity;
    private double averageProductivity;
}
