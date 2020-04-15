package tqs.airquality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tqs.airquality.model.Continent;
import tqs.airquality.repository.ContinentRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class ContinentService {

    @Autowired
    private ContinentRepository continentRepository;


    public List<Continent> getAllContinents() throws IOException {

        List<Continent> allContinents = continentRepository.findAll();

        if(allContinents.isEmpty()) {
            File resource = new ClassPathResource("data/continents.csv").getFile();
            String[] loadedContinents = (new String(Files.readAllBytes(resource.toPath()))).split("\n");
            for (String c : loadedContinents) {
                Continent continent = new Continent(c);
                continentRepository.save(continent);
                allContinents.add(continent);
            }
        }
        return allContinents;
    }

    public Continent createContinent(Continent continent) {
        return continentRepository.save(continent);
    }
}
