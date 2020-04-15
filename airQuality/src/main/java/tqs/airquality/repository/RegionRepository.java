package tqs.airquality.repository;

import tqs.airquality.model.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    public Region findByName(String name);
}
