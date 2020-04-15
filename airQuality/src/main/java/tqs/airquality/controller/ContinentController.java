package tqs.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tqs.airquality.model.Continent;
import tqs.airquality.service.ContinentService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping("/continents")
    public List<Continent> getAllContinents() throws IOException {
        return continentService.getAllContinents();
    }

    @PostMapping("/continents")
    public Continent createContinent(@Valid @RequestBody Continent continent) {
        return continentService.createContinent(continent);
    }

}
