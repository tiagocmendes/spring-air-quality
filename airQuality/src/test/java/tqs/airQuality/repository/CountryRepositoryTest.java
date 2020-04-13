package tqs.airQuality.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tqs.airQuality.model.Country;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CountryRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void injectedComponentAreNotNullTest() {
        assertNotEquals(null, entityManager);
        assertNotEquals(null, countryRepository);
    }

    @Test
    public void whenFindByName_thenReturnCountry() {
        Country country = new Country("Country1", "Continent1", "Flag1");
        entityManager.persistAndFlush(country);

        Country foundCountry = countryRepository.findByName(country.getName());
        assertEquals(foundCountry.getName(), foundCountry.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Country country = countryRepository.findByName("notInEarth");
        assertEquals(null, country);
    }

    @Test
    public void whenFindById_thenReturnContinent() {
        Country country = new Country("Country1", "Continent1", "Flag1");
        entityManager.persistAndFlush(country);

        Country foundCountry = countryRepository.findById(country.getId()).orElse(null);
        assertEquals(foundCountry.getName(), country.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Country country = countryRepository.findById((long) -1).orElse(null);
        assertEquals(null, country);
    }

    @Test
    public void givenSetOfContinents_whenFindAll_ThenReturnAllContinents() {
        List<Country> insertedCountries = new ArrayList<>();
        insertedCountries.add(new Country("Country1", "Continent1", "Flag1"));
        insertedCountries.add(new Country("Country2", "Continent2", "Flag2"));;
        insertedCountries.add(new Country("Country3", "Continent3", "Flag3"));

        for(Country c: insertedCountries) {
            entityManager.persist(c);
        }
        entityManager.flush();

        List<Country> allCountries = countryRepository.findAll();

        // because my database is not empty, I can only test
        // the size of the returned List in this way
        int size = 0;
        for(Country c: allCountries) {
            if (insertedCountries.contains(c)) {
                size++;
            }
        }

        assertEquals(size, insertedCountries.size());
        for(Country c : insertedCountries)
            assertEquals(true, allCountries.contains(c));
    }


}
