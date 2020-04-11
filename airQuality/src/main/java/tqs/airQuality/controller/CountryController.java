package tqs.airQuality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;
import tqs.airQuality.repository.CountryRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping("/countries/{continent}")
    public List<Country> getCountriesByContinent(@PathVariable(value = "continent") String continent) {

        List<Country> countries = countryRepository.findAll();

        List<Country> returnCountries = new ArrayList<>();
        for(Country c : countries) {
            if(c.getContinent().toLowerCase().equals(continent.toLowerCase())) {
                returnCountries.add(c);
            }
        }

        return returnCountries;
    }

    @PostMapping("/countries")
    public List<Country> createCountry(@Valid @RequestBody List<Country> countries) {
        return countryRepository.saveAll(countries);
    }
}
