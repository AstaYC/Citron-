package com.astayc.citron.Controller;

import com.astayc.citron.DTO.FieldPerformanceDTO;
import com.astayc.citron.DTO.HarvestDTO;
import com.astayc.citron.DTO.ProductivityReportDTO;
import com.astayc.citron.Entity.Field;
import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Mapper.HarvestMapper;
import com.astayc.citron.Repository.FieldRepository;
import com.astayc.citron.Service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper; // Inject if using a mapper class

    @PostMapping
    public ResponseEntity<HarvestDTO> createHarvest(@RequestBody HarvestDTO harvestDTO) {
        // Convert DTO to Entity
        Harvest harvest = new Harvest();
        harvest.setHarvestDate(harvestDTO.getHarvestDate());
        Field field = new Field();
        field.setId(harvestDTO.getFieldId());
        harvest.setField(field);

        // Save harvest
        Harvest savedHarvest = harvestService.createHarvest(harvest);

        // Convert Entity to DTO for response
        HarvestDTO responseDTO = harvestMapper.toDTO(savedHarvest); // If using mapper
        // Or: HarvestDTO responseDTO = harvestService.toDTO(savedHarvest); // If in service

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/reports/productivity")
    public ResponseEntity<List<ProductivityReportDTO>> getProductivityReport() {
        return ResponseEntity.ok(harvestService.generateProductivityReport());
    }

    @GetMapping("/reports/field-performance")
    public ResponseEntity<List<FieldPerformanceDTO>> getFieldPerformance(@RequestParam double threshold) {
        return ResponseEntity.ok(harvestService.analyzeFieldPerformance(threshold));
    }

}

