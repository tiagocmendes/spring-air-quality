package tqs.airQuality.controller;

import tqs.airQuality.exception.RegionNotFoundException;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.RegionRepository;
import tqs.airQuality.service.RegionService;
import tqs.airQuality.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class RegionController {

    @Autowired
    RegionRepository regionRepository;


    @GetMapping("/regions/{name}")
    public Region getRegionByName(@PathVariable(value = "name") String name) throws IOException, URISyntaxException {
        return RegionService.getRegionByName(name);
    }
    
}
