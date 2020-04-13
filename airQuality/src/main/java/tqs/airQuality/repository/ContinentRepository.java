package tqs.airQuality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tqs.airQuality.model.Continent;

@Repository
@Transactional
public interface ContinentRepository extends JpaRepository<Continent, Long> {
    public Continent findByName(String name);
}