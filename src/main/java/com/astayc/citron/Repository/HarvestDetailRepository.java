package com.astayc.citron.Repository;

import com.astayc.citron.Entity.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, Long> {
    // Add custom query methods if required
}
