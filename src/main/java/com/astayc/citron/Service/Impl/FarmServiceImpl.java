package com.astayc.citron.Service.Impl;
import com.astayc.citron.DTO.FarmDTO;
import com.astayc.citron.Entity.Farm;
import com.astayc.citron.Mapper.FarmMapper;
import com.astayc.citron.Repository.FarmRepository;
import com.astayc.citron.Service.FarmService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmServiceImpl implements FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    public FarmServiceImpl(FarmRepository farmRepository, FarmMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;
    }

    @Override
    public FarmDTO createFarm(FarmDTO farmDto) {
        Farm farm = farmMapper.toEntity(farmDto);
        return farmMapper.toDto(farmRepository.save(farm));
    }

    @Override
    public List<FarmDTO> getAllFarms() {
        return farmMapper.toDtoList(farmRepository.findAll());
    }

    @Override
    public FarmDTO getFarmById(Long id) {
        Farm farm = farmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farm not found with id: " + id));
        return farmMapper.toDto(farm);
    }
}