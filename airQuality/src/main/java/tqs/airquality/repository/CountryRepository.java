package tqs.airquality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.airquality.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    public Country findByName(String name);
}
