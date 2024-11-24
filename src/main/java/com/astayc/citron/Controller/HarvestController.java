package com.astayc.citron.Controller;

import com.astayc.citron.DTO.HarvestDTO;
import com.astayc.citron.Service.HarvestService;
import com.astayc.citron.Mapper.HarvestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvests")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;

    @PostMapping
    public ResponseEntity<HarvestDTO> createHarvest(@RequestBody HarvestDTO harvestDTO) {
        var harvest = harvestMapper.toEntity(harvestDTO);
        var createdHarvest = harvestService.createHarvest(harvest, List.of(), List.of());
        return ResponseEntity.ok(harvestMapper.toDTO(createdHarvest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDTO> getHarvest(@PathVariable Long id) {
        var harvest = harvestService.getHarvestById(id);
        return ResponseEntity.ok(harvestMapper.toDTO(harvest));
    }
}
