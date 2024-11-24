package com.astayc.citron.Service;

import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Entity.HarvestDetail;

import java.util.List;

public interface HarvestService {
    Harvest createHarvest(Harvest harvest, List<HarvestDetail> details, List<Long> treeIds);

    Harvest getHarvestById(Long id);

    List<Harvest> getAllHarvests();

    void deleteHarvest(Long id);
}
