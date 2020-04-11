package tqs.airQuality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.airQuality.model.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {}