package tqs.airQuality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tqs.airQuality.model.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> { }
