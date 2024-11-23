package com.astayc.citron.Controller;

import com.astayc.citron.DTO.FarmDTO;
import com.astayc.citron.Service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @PostMapping
    public FarmDTO createFarm(@RequestBody FarmDTO farmDto) {
        return farmService.createFarm(farmDto);
    }

    @GetMapping
    public List<FarmDTO> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{id}")
    public FarmDTO getFarmById(@PathVariable Long id) {
        return farmService.getFarmById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFarm(@PathVariable Long id) {
        farmService.deleteFarm(id);
    }
}
