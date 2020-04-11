package tqs.airQuality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tqs.airQuality.cache.Cache;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;
import tqs.airQuality.repository.ContinentRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContinentController {

    @Autowired
    ContinentRepository continentRepository;

    private static long TIME_TO_LIVE = 120;
    private static long TIMER = 120;
    private static Cache<String, Continent> regionCache = new Cache<>(TIME_TO_LIVE, TIMER);

    @GetMapping("/continents")
    public List<Continent> getAllContinents() {
        return continentRepository.findAll();
    }

    @PostMapping("/continents")
    public Continent createContinent(@Valid @RequestBody Continent continent) {
        return continentRepository.save(continent);
    }


}
