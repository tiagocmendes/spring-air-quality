package tqs.airQuality.controller;

import tqs.airQuality.exception.RegionNotFoundException;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.RegionRepository;
import tqs.airQuality.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

import org.apache.http.ParseException;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    
    // Get All Notes
    @GetMapping("/region")
    public List<Region> getAllNotes() {
        return regionRepository.findAll();
    }

    @GetMapping("/api/{name}")
    public void getFromAPI(@PathVariable(value = "name") String name) throws URISyntaxException, IOException {
        HttpClient httpClient = new AirQualityHttpClient();
        URIBuilder uriBuilder = new URIBuilder("https://api.waqi.info/feed/" + name);
        String response = httpClient.get(uriBuilder.build().toString());
        System.out.println("RESPONSE: " + response);
    }

    // Create a new Note
    @PostMapping("/region")
    public Region createNote(@Valid @RequestBody Region region) {
        return regionRepository.save(region);
    }

    // Get a Single Note
    @GetMapping("/region/{id}")
    public Region getNoteById(@PathVariable(value = "id") Long regionId) throws RegionNotFoundException {
        return regionRepository.findById(regionId).orElseThrow(() -> new RegionNotFoundException(regionId));
    }


    // Delete a Note
    @DeleteMapping("/region/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value="id") Long regionId) throws RegionNotFoundException {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new RegionNotFoundException(regionId));
        regionRepository.delete(region);

        return ResponseEntity.ok().build();
    }

}
