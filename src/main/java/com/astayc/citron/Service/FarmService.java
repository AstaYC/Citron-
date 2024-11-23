package com.astayc.citron.Service;

import com.astayc.citron.DTO.FarmDTO;

import java.util.List;

public interface FarmService {
    FarmDTO createFarm(FarmDTO farmDto);

    List<FarmDTO> getAllFarms();

    FarmDTO getFarmById(Long id);

    void deleteFarm(Long id);

    double calculateTotalArea();
}
