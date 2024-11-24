package com.astayc.citron.DTO;

import com.astayc.citron.Entity.Enum.Season;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HarvestDTO {
    private Long id;
    private LocalDate harvestDate;
    private double totalQuantity;
    private Season season;
    private Long fieldId;
    private List<HarvestDetailDTO> harvestDetails;
}

@Data
class HarvestDetailDTO {
    private Long id;
    private double quantity;
    private Long treeId;
}
