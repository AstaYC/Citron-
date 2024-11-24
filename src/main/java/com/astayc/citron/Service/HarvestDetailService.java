package com.astayc.citron.Service;

import com.astayc.citron.Entity.HarvestDetail;

public interface HarvestDetailService {
    HarvestDetail createHarvestDetail(HarvestDetail detail);
    HarvestDetail getHarvestDetailById(Long id);
}
