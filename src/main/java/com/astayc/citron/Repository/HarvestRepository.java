package com.astayc.citron.Repository;

import com.astayc.citron.Entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    // Add any custom query methods if needed
}
