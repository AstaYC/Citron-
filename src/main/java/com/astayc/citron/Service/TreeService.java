package com.astayc.citron.Service;

import com.astayc.citron.Entity.Tree;
import com.astayc.citron.DTO.TreeDTO;

import java.util.List;

public interface TreeService {
    Tree plantTree(Long fieldId, TreeDTO treeDTO);

    List<Tree> getTreesByField(Long fieldId);

    double getTotalProductivityByField(Long fieldId);
}
