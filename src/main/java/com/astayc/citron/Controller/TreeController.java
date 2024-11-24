package com.astayc.citron.Controller;

import com.astayc.citron.DTO.TreeDTO;
import com.astayc.citron.Entity.Tree;
import com.astayc.citron.Service.TreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/trees")
@RequiredArgsConstructor
public class TreeController {

    private final TreeService treeService;

    // Plant a new tree in a specific field
    @PostMapping("/field/{fieldId}")
    public ResponseEntity<TreeDTO> plantTree(@PathVariable Long fieldId, @RequestBody @Valid TreeDTO treeDTO) {
        Tree tree = treeService.plantTree(fieldId, treeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(tree));
    }

    // Get all trees in a specific field
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<TreeDTO>> getTreesByField(@PathVariable Long fieldId) {
        List<Tree> trees = treeService.getTreesByField(fieldId);
        if (trees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(convertToDTOList(trees));
    }

    // Get total productivity for a specific field
    @GetMapping("/field/{fieldId}/productivity")
    public ResponseEntity<Double> getTotalProductivityByField(@PathVariable Long fieldId) {
        double totalProductivity = treeService.getTotalProductivityByField(fieldId);
        return ResponseEntity.ok(totalProductivity);
    }

    // Utility methods to map Entity <-> DTO
    private TreeDTO convertToDTO(Tree tree) {
        // Replace with a proper mapping logic or ModelMapper usage
        return TreeDTO.builder()
                .id(tree.getId())
                .datePlantation(tree.getDatePlantation())
                .age(tree.getAge())
                .productivity(tree.getProductivity())
                .fieldId(tree.getField().getId())
                .build();
    }

    private List<TreeDTO> convertToDTOList(List<Tree> trees) {
        return trees.stream().map(this::convertToDTO).toList();
    }
}
