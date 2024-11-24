package com.astayc.citron.Service.Impl;

import com.astayc.citron.DTO.TreeDTO;
import com.astayc.citron.Entity.Tree;
import com.astayc.citron.Entity.Field;
import com.astayc.citron.Exception.ResourceNotFoundException;
import com.astayc.citron.Repository.TreeRepository;
import com.astayc.citron.Repository.FieldRepository;
import com.astayc.citron.Service.TreeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {

    private final TreeRepository treeRepository;
    private final FieldRepository fieldRepository;

    @Override
    public Tree plantTree(Long fieldId, TreeDTO treeDTO) {
        var field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new IllegalArgumentException("Field with ID " + fieldId + " not found"));

        Tree tree = Tree.builder()
                .datePlantation(treeDTO.getDatePlantation())
                .field(field)
                .build();

        tree.updateDerivedFields(); // Calculate age and productivity
        return treeRepository.save(tree);
    }

    @Override
    public List<Tree> getTreesByField(Long fieldId) {
        return treeRepository.findByFieldId(fieldId);
    }

    @Override
    public double getTotalProductivityByField(Long fieldId) {
        return treeRepository.findByFieldId(fieldId)
                .stream()
                .mapToDouble(Tree::getProductivity) // Use the correct field name
                .sum();
    }
}

