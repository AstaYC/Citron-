package com.astayc.citron.Service.Impl;

import com.astayc.citron.Entity.HarvestDetail;
import com.astayc.citron.Repository.HarvestDetailRepository;
import com.astayc.citron.Service.HarvestDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HarvestDetailServiceImpl implements HarvestDetailService {

    private final HarvestDetailRepository harvestDetailRepository;

    @Override
    public HarvestDetail createHarvestDetail(HarvestDetail detail) {
        return harvestDetailRepository.save(detail);
    }

    @Override
    public HarvestDetail getHarvestDetailById(Long id) {
        return harvestDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Harvest detail not found"));
    }
}
