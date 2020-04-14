package tqs.airQuality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;
import tqs.airQuality.repository.CountryRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() throws IOException {

        List<Country> allCountries = countryRepository.findAll();

        if(allCountries.isEmpty()) {
            File resource = new ClassPathResource("data/countries.csv").getFile();
            String[] loadedCountries = (new String(Files.readAllBytes(resource.toPath()))).split("\n");
            for (String c : loadedCountries) {
                String[] fields = c.split(",");
                Country country = new Country(fields[0], fields[1], fields[2]);
                countryRepository.save(country);
                allCountries.add(country);
            }
        }

        return allCountries;
    }

    public List<Country> getCountriesByContinent(String continent) {
        List<Country> allCountries = countryRepository.findAll();
        List<Country> countriesByContinent = new ArrayList<>();
        for(Country c : allCountries) {
            if(c.getContinent().toLowerCase().equals(continent.toLowerCase())) {
                countriesByContinent.add(c);
            }
        }
        return countriesByContinent;
    }

    public List<Country> createCountries(List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

}
