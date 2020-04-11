package tqs.airQuality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.airQuality.cache.Cache;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.CountryRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    private static long TIME_TO_LIVE = 120;
    private static long TIMER = 120;
    private static Cache<String, List<Country>> regionCache = new Cache<>(TIME_TO_LIVE, TIMER);

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        List<Country> allCountries = regionCache.get("allCountries");

        if(allCountries == null) {
            allCountries = countryRepository.findAll();
            regionCache.put("allCountries", allCountries);
        }

        return allCountries;
    }

    @GetMapping("/countries/{continent}")
    public List<Country> getCountriesByContinent(@PathVariable(value = "continent") String continent) {

        List<Country> countriesByContinent = regionCache.get("countriesByContinent");
        
        if(countriesByContinent == null) {
            List<Country> countries = countryRepository.findAll();

            countriesByContinent = new ArrayList<>();
            for(Country c : countries) {
                if(c.getContinent().toLowerCase().equals(continent.toLowerCase())) {
                    countriesByContinent.add(c);
                }
            }

            regionCache.put("countriesByContinent", countriesByContinent);
        }

        return countriesByContinent;
    }

    @PostMapping("/countries")
    public List<Country> createCountry(@Valid @RequestBody List<Country> countries) {
        return countryRepository.saveAll(countries);
    }
}
