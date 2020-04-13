package tqs.airQuality.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tqs.airQuality.model.Region;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RegionRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RegionRepository regionRepository;

    @Test
    void injectedComponentAreNotNullTest() {
        assertNotEquals(null, entityManager);
        assertNotEquals(null, regionRepository);
    }

    @Test
    public void whenFindByName_thenReturnRegion() {
        Region region = new Region("Region",(double) -40.0, (double) 8.0, "url", 10, "co2", new JSONObject(), "2020-04-15", "GMT+2");
        entityManager.persistAndFlush(region);

        Region regionCountry = regionRepository.findByName(region.getName());
        assertEquals(regionCountry.getName(), region.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
        Region region = regionRepository.findByName("notInEarth");
        assertEquals(null, region);
    }

    @Test
    public void whenFindById_thenReturnRegion() {
        Region region = new Region("Region",(double) -40.0, (double) 8.0, "url", 10, "co2", new JSONObject(), "2020-04-15", "GMT+2");
        entityManager.persistAndFlush(region);

        Region foundRegion = regionRepository.findById(region.getId()).orElse(null);
        assertEquals(foundRegion.getName(), region.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Region region = regionRepository.findById((long) -1).orElse(null);
        assertEquals(null, region);
    }

    @Test
    public void givenSetOfCountries_whenFindAll_ThenReturnAllRegions() {
        List<Region> insertedRegions = new ArrayList<>();
        insertedRegions.add(new Region("Region1",(double) -40.0, (double) 8.0, "url", 10, "co2", new JSONObject(), "2020-04-15", "GMT+2"));
        insertedRegions.add(new Region("Region2",(double) -40.0, (double) 8.0, "url", 10, "co2", new JSONObject(), "2020-04-15", "GMT+2"));
        insertedRegions.add(new Region("Region3",(double) -40.0, (double) 8.0, "url", 10, "co2", new JSONObject(), "2020-04-15", "GMT+2"));

        for(Region r: insertedRegions) {
            entityManager.persist(r);
        }
        entityManager.flush();

        List<Region> allRegions = regionRepository.findAll();

        // because my database is not empty, I can only test
        // the size of the returned List in this way
        int size = 0;
        for(Region r: allRegions) {
            if (insertedRegions.contains(r)) {
                size++;
            }
        }

        assertEquals(size, insertedRegions.size());
        for(Region r : insertedRegions)
            assertEquals(true, allRegions.contains(r));
    }

}
