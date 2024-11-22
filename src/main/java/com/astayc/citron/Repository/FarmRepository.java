    package com.astayc.citron.Repository;
    import com.astayc.citron.Entity.Farm;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface FarmRepository extends JpaRepository<Farm, Long> {
    }
