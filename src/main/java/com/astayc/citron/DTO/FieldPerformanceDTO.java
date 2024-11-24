package com.astayc.citron.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldPerformanceDTO {
    private String fieldName;
    private double totalProductivity;
    private boolean isUnderperforming;
}
