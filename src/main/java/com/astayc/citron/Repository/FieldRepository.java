package com.astayc.citron.Repository;

import com.astayc.citron.Entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFarmId(Long farmId); // Retrieve fields associated with a specific farm
}