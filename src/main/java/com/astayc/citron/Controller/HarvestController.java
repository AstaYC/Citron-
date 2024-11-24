package com.astayc.citron.Controller;

import com.astayc.citron.DTO.HarvestDTO;
import com.astayc.citron.Entity.Field;
import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Mapper.HarvestMapper;
import com.astayc.citron.Repository.FieldRepository;
import com.astayc.citron.Service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;
    private final FieldRepository fieldRepository;

    @PostMapping
    public ResponseEntity<HarvestDTO> createHarvest(@RequestBody HarvestDTO harvestDTO) {
        var harvest = new Harvest();
        harvest.setHarvestDate(harvestDTO.getHarvestDate());

        // Fetch and set the field by ID
        var field = fieldRepository.findById(harvestDTO.getFieldId())
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + harvestDTO.getFieldId()));
        harvest.setField(field);

        var createdHarvest = harvestService.createHarvest(harvest);
        return ResponseEntity.ok(harvestMapper.toDTO(createdHarvest));
    }

}
