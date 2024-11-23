package com.astayc.citron.Service.Impl;

import com.astayc.citron.DTO.FarmDTO;
import com.astayc.citron.Entity.Farm;
import com.astayc.citron.Repository.FarmRepository;
import com.astayc.citron.Service.FarmService;
import com.astayc.citron.Mapper.FarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FarmServiceImpl implements FarmService {

    @Autowired
    private FarmRepository farmRepository;

    @Autowired
    private FarmMapper farmMapper;

    @Override
    public FarmDTO createFarm(FarmDTO farmDto) {
        // Convert DTO to Entity
        Farm farm = farmMapper.toEntity(farmDto);
        // Save entity in the database
        Farm savedFarm = farmRepository.save(farm);
        // Convert back to DTO and return
        return farmMapper.toDTO(savedFarm);
    }

    @Override
    public List<FarmDTO> getAllFarms() {
        // Retrieve all farms and map them to DTOs
        return farmRepository.findAll()
                .stream()
                .map(farmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FarmDTO getFarmById(Long id) {
        // Find the farm or throw an exception if not found
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with ID: " + id));
        // Convert to DTO and return
        return farmMapper.toDTO(farm);
    }

    @Override
    public void deleteFarm(Long id) {
        // Delete farm by ID
        if (!farmRepository.existsById(id)) {
            throw new RuntimeException("Farm not found with ID: " + id);
        }
        farmRepository.deleteById(id);
    }

    @Override
    public double calculateTotalArea() {
        return farmRepository.findAll()
                .stream()
                .mapToDouble(Farm::getArea)
                .sum();
    }
}
