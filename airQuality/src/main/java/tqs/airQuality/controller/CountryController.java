package tqs.airQuality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tqs.airQuality.model.Country;
import tqs.airQuality.service.CountryService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{continent}")
    public List<Country> getCountriesByContinent(@PathVariable(value = "continent") String continent) {
        return countryService.getCountriesByContinent(continent);
    }

    @PostMapping("/countries")
    public List<Country> createCountries(@Valid @RequestBody List<Country> countries) {
        return countryService.createCountries(countries);
    }

}
