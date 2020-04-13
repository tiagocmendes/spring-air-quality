package tqs.airQuality.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tqs.airQuality.model.Continent;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ContinentRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContinentRepository continentRepository;

    @Test
    void injectedComponentAreNotNullTest() {
        assertNotEquals(null, entityManager);
        assertNotEquals(null, continentRepository);
    }

    @Test
    public void whenFindByName_thenReturnContinent() {
        Continent continent = new Continent("ANTARTIDA");
        entityManager.persistAndFlush(continent);

        Continent foundContinent = continentRepository.findByName(continent.getName());
        assertEquals(foundContinent.getName(), continent.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Continent continent = continentRepository.findByName("notInEarth");
        assertEquals(null, continent);
    }

    @Test
    public void whenFindById_thenReturnContinent() {
        Continent continent = new Continent("ANTARTIDA");
        entityManager.persistAndFlush(continent);

        Continent foundContinent = continentRepository.findById(continent.getId()).orElse(null);
        assertEquals(foundContinent.getName(), continent.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Continent continent = continentRepository.findById((long) -1).orElse(null);
        assertEquals(null, continent);
    }

    @Test
    public void givenSetOfContinents_whenFindAll_ThenReturnAllContinents() {
        List<Continent> insertedContinents = new ArrayList<>();
        insertedContinents.add(new Continent("Continent1"));
        insertedContinents.add(new Continent("Continent2"));
        insertedContinents.add(new Continent("Continent3"));

        for(Continent c: insertedContinents) {
            entityManager.persist(c);
        }
        entityManager.flush();

        List<Continent> allContinents = continentRepository.findAll();

        // because my database is not empty, I can only test
        // the size of the returned List in this way
        int size = 0;
        for(Continent c: allContinents) {
            if (insertedContinents.contains(c)) {
                size++;
            }
        }

        assertEquals(size, insertedContinents.size());
        for(Continent c : insertedContinents)
            assertEquals(true, allContinents.contains(c));
    }


}
