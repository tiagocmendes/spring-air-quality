package tqs.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tqs.airquality.model.Country;
import tqs.airquality.service.CountryService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping("/countries")
    public List<Country> getAllCountries() throws IOException {
        return countryService.getAllCountries();
    }

    @GetMapping("/countries/{continent}")
    public List<Country> getCountriesByContinent(@PathVariable(value = "continent") String continent) throws IOException {
        return countryService.getCountriesByContinent(continent);
    }

    @PostMapping("/countries")
    public List<Country> createCountries(@Valid @RequestBody List<Country> countries) {
        return countryService.createCountries(countries);
    }

}
