package com.astayc.citron.Repository;

import com.astayc.citron.Entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreeRepository extends JpaRepository<Tree, Long> {

    // Custom query to fetch trees by field ID
    List<Tree> findByFieldId(Long fieldId);
}
