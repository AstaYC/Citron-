package com.astayc.citron.Service;

import com.astayc.citron.DTO.FieldPerformanceDTO;
import com.astayc.citron.DTO.ProductivityReportDTO;
import com.astayc.citron.Entity.Harvest;

import java.util.List;

public interface HarvestService {
    Harvest createHarvest(Harvest harvest);
    List<ProductivityReportDTO> generateProductivityReport();
    public List<FieldPerformanceDTO> analyzeFieldPerformance(double threshold);

}
