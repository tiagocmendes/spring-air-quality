package tqs.airQuality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tqs.airQuality.model.Country;
import tqs.airQuality.repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
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
