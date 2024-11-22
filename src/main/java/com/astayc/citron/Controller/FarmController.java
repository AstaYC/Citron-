package com.astayc.citron.Controller;
import com.astayc.citron.Entity.Farm;
import com.astayc.citron.Repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmRepository farmRepository;

    // Create a new farm
    @PostMapping
    public Farm createFarm(@RequestBody Farm farm) {
        return farmRepository.save(farm);
    }

    // Get all farms
    @GetMapping
    public List<Farm> getAllFarms() {
        return farmRepository.findAll();
    }

    // Get a specific farm by ID
    @GetMapping("/{id}")
    public Farm getFarmById(@PathVariable Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id: " + id));
    }
}