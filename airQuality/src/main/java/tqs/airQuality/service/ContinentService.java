package tqs.airQuality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.airQuality.model.Continent;
import tqs.airQuality.repository.ContinentRepository;

import java.util.List;

@Service
public class ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    public List<Continent> getAllContinents() {
        return continentRepository.findAll();
    }

    public Continent createContinent(Continent continent) {
        return continentRepository.save(continent);
    }
}
