package com.astayc.citron.Service.Impl;

import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Entity.HarvestDetail;
import com.astayc.citron.Repository.HarvestDetailRepository;
import com.astayc.citron.Repository.HarvestRepository;
import com.astayc.citron.Repository.TreeRepository;
import com.astayc.citron.Service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final HarvestDetailRepository harvestDetailRepository;
    private final TreeRepository treeRepository;

    @Override
    public Harvest createHarvest(Harvest harvest, List<HarvestDetail> details, List<Long> treeIds) {
        // Save the harvest first
        Harvest savedHarvest = harvestRepository.save(harvest);

        // Map treeIds to HarvestDetails and save them
        details.forEach(detail -> {
            var tree = treeRepository.findById(detail.getTree().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Tree not found with ID: " + detail.getTree().getId()));
            detail.setHarvest(savedHarvest);
            detail.setTree(tree);
            harvestDetailRepository.save(detail);
        });

        return savedHarvest;
    }

    @Override
    public Harvest getHarvestById(Long id) {
        return harvestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Harvest not found with ID: " + id));
    }

    @Override
    public List<Harvest> getAllHarvests() {
        return harvestRepository.findAll();
    }

    @Override
    public void deleteHarvest(Long id) {
        Harvest harvest = getHarvestById(id);
        harvestRepository.delete(harvest);
    }
}
