package tqs.airQuality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.airQuality.cache.Cache;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.CountryRepository;
import tqs.airQuality.service.RegionService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    private static long TIME_TO_LIVE = 120;
    private static long TIMER = 120;
    private static Cache<String, List<Country>> countryCache = new Cache<>(TIME_TO_LIVE, TIMER);

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        List<Country> allCountries = countryCache.get("allCountries");

        if(allCountries == null) {
            allCountries = countryRepository.findAll();
            countryCache.put("allCountries", allCountries);
        }

        return allCountries;
    }

    @GetMapping("/countries/{continent}")
    public List<Country> getCountriesByContinent(@PathVariable(value = "continent") String continent) {

        List<Country> countriesByContinent = countryCache.get(continent);

        if(countriesByContinent == null) {
            List<Country> countries = countryRepository.findAll();

            countriesByContinent = new ArrayList<>();
            for(Country c : countries) {
                if(c.getContinent().toLowerCase().equals(continent.toLowerCase())) {
                    countriesByContinent.add(c);
                }
            }

            countryCache.put(continent, countriesByContinent);
        }

        return countriesByContinent;
    }

    @PostMapping("/countries")
    public List<Country> createCountry(@Valid @RequestBody List<Country> countries) {
        return countryRepository.saveAll(countries);
    }

    @GetMapping("/countries/cache")
    public Map<String, Object> getCacheDetails() {
        return countryCache.getDetails();
    }
}
