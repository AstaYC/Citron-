package com.astayc.citron.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldDTO {

    @NotNull(message = "Field area is required.")
    @Min(value = 0, message = "Field area must be greater than or equal to 0.1 hectares.")
    private Double area; // in hectares
    private Long id; // Add the ID field
    private int treeSpacing;
}