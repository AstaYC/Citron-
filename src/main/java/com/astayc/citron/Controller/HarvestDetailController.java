package com.astayc.citron.Controller;

import com.astayc.citron.DTO.HarvestDetailDTO;
import com.astayc.citron.Mapper.HarvestDetailMapper;
import com.astayc.citron.Service.HarvestDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/harvest-details")
@RequiredArgsConstructor
public class HarvestDetailController {

    private final HarvestDetailService harvestDetailService;
    private final HarvestDetailMapper harvestDetailMapper;

    @PostMapping
    public ResponseEntity<HarvestDetailDTO> createHarvestDetail(@RequestBody HarvestDetailDTO dto) {
        var detail = harvestDetailMapper.toEntity(dto);
        var createdDetail = harvestDetailService.createHarvestDetail(detail);
        return ResponseEntity.ok(harvestDetailMapper.toDTO(createdDetail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailDTO> getHarvestDetail(@PathVariable Long id) {
        var detail = harvestDetailService.getHarvestDetailById(id);
        return ResponseEntity.ok(harvestDetailMapper.toDTO(detail));
    }
}
