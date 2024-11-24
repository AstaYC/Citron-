package com.astayc.citron.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    private Long id;
    private LocalDate datePlantation;
    private int age;
    private double productivity;
    private Long fieldId; // To associate the tree with a specific field
}

