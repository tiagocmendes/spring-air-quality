package tqs.airQuality.controller;

import tqs.airQuality.exception.RegionNotFoundException;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.RegionRepository;
import tqs.airQuality.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.validation.Valid;
import java.util.HashMap;
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
    public Region getFromAPI(@PathVariable(value = "name") String name) throws URISyntaxException, IOException {
        System.out.println(name);
        HttpClient httpClient = new AirQualityHttpClient();
        URIBuilder uriBuilder = new URIBuilder("https://api.waqi.info/feed/" + name);
        String response = httpClient.get(uriBuilder.build().toString());

        try {
            JSONObject jsonResponse = (JSONObject) new JSONObject(response);

            JSONObject data = (JSONObject) jsonResponse.get("data");


            // City
            JSONObject city = (JSONObject) data.get("city");

            String cityName = (String) city.get("name");

            JSONArray geo = new JSONArray(city.get("geo").toString());
            Double latitude = Double.parseDouble(geo.get(0).toString());
            Double longitude = Double.parseDouble(geo.get(1).toString());

            String url = (String) city.get("url");


            // Air Quality Indicator
            Integer aqi = (Integer) data.get("aqi");



            // Pollutants
            String primaryPollutant = (String) data.get("dominentpol");
            JSONObject pollutants = (JSONObject) data.get("iaqi");


            // Time
            JSONObject time = (JSONObject) data.get("time");
            String timestamp = (String) time.get("s");
            String timezone = (String) time.get("tz");

            Region region = new Region(name, latitude, longitude, url, aqi, primaryPollutant, pollutants, timestamp, timezone);

            return region;
        }
        catch (Exception e) {
            throw new Error(e);
        }

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
