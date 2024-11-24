package com.astayc.citron.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HarvestDetailDTO {
    private Long id;
    private double quantity;
    private Long harvestId; // Parent Harvest ID
    private Long treeId;    // Reference to the Tree
}
