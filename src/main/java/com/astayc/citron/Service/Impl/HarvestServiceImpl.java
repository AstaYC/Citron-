package com.astayc.citron.Service.Impl;

import com.astayc.citron.DTO.FieldPerformanceDTO;
import com.astayc.citron.DTO.HarvestDTO;
import com.astayc.citron.DTO.ProductivityReportDTO;
import com.astayc.citron.Entity.Enum.Season;
import com.astayc.citron.Entity.Harvest;
import com.astayc.citron.Entity.HarvestDetail;
import com.astayc.citron.Entity.Tree;
import com.astayc.citron.Repository.HarvestDetailRepository;
import com.astayc.citron.Repository.HarvestRepository;
import com.astayc.citron.Repository.TreeRepository;
import com.astayc.citron.Service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;
    private final HarvestDetailRepository harvestDetailRepository;

    @Transactional
    public Harvest createHarvest(Harvest harvest) {
        // 1. Calculate the season based on the harvest date
        harvest.setSeason(determineSeason(harvest.getHarvestDate()));

        // 2. Save the Harvest entity first to generate its ID
        Harvest savedHarvest = harvestRepository.save(harvest);

        // 3. Get all trees in the specified field
        List<Tree> trees = treeRepository.findByFieldId(harvest.getField().getId());

        double totalQuantity = 0.0;

        for (Tree tree : trees) {
            // 4. Adjust the tree's age based on the harvest date
            int treeAgeAtHarvest = calculateTreeAge(tree, harvest.getHarvestDate());
            tree.setAge(treeAgeAtHarvest);

            // 5. Calculate productivity based on tree's age
            double treeProductivity = calculateTreeProductivity(tree);

            // 6. Create a HarvestDetail for the tree
            HarvestDetail harvestDetail = new HarvestDetail();
            harvestDetail.setTree(tree);
            harvestDetail.setHarvest(savedHarvest); // Use the saved Harvest entity
            harvestDetail.setQuantity(treeProductivity);

            // Save HarvestDetail
            harvestDetailRepository.save(harvestDetail);

            // Accumulate total quantity
            totalQuantity += treeProductivity;
        }

        // 7. Set the total quantity for the harvest
        savedHarvest.setTotalQuantity(totalQuantity);

        // 8. Save and return the updated Harvest
        return harvestRepository.save(savedHarvest);
    }

    private Season determineSeason(LocalDate date) {
        int month = date.getMonthValue();
        if (month >= 3 && month <= 5) return Season.SPRING;
        if (month >= 6 && month <= 8) return Season.SUMMER;
        if (month >= 9 && month <= 11) return Season.FALL;
        return Season.WINTER;
    }

    private int calculateTreeAge(Tree tree, LocalDate harvestDate) {
        return Period.between(tree.getDatePlantation(), harvestDate).getYears();
    }

    private double calculateTreeProductivity(Tree tree) {
        if (tree.getAge() < 3) {
            return 2.5; // Young trees
        } else if (tree.getAge() <= 10) {
            return 12.0; // Peak productivity
        } else {
            return 20.0; // Older trees
        }
    }

    @Override
    public List<ProductivityReportDTO> generateProductivityReport() {
        List<Harvest> harvests = harvestRepository.findAll();

        return harvests.stream()
                .collect(Collectors.groupingBy(
                        harvest -> new AbstractMap.SimpleEntry<>(
                                harvest.getField().getId(), // Replace 'getId()' if necessary
                                harvest.getSeason()
                        ),
                        Collectors.summarizingDouble(Harvest::getTotalQuantity)
                ))
                .entrySet()
                .stream()
                .map(entry -> new ProductivityReportDTO(
                        entry.getKey().getKey().toString(), // Field identifier (e.g., ID)
                        entry.getKey().getValue().toString(), // Season
                        entry.getValue().getSum(),
                        entry.getValue().getAverage()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<FieldPerformanceDTO> analyzeFieldPerformance(double threshold) {
        List<Harvest> harvests = harvestRepository.findAll();

        return harvests.stream()
                .collect(Collectors.groupingBy(
                        harvest -> harvest.getField().getId(), // Replace with a valid property
                        Collectors.summingDouble(Harvest::getTotalQuantity)
                ))
                .entrySet()
                .stream()
                .map(entry -> new FieldPerformanceDTO(
                        entry.getKey().toString(), // Field identifier (e.g., ID)
                        entry.getValue(), // Total productivity
                        entry.getValue() < threshold // Underperforming check
                ))
                .collect(Collectors.toList());
    }



}
