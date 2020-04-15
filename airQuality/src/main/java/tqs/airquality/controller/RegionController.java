package tqs.airquality.controller;

import tqs.airquality.model.Region;
import tqs.airquality.repository.RegionRepository;
import tqs.airquality.service.RegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
public class RegionController {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    RegionService regionService;

    @GetMapping("/regions/{name}")
    public List<Region> getRegionByName(@PathVariable(value = "name") String name) throws IOException, URISyntaxException {
        List<Region> regionList = new ArrayList<>();
        Region region = regionService.getRegionByName(name);
        if(region != null)
            regionList.add(region);
        return regionList;
    }


    @GetMapping("/here")
    public List<Region> getRegionByCurrentLocation() throws IOException, URISyntaxException {
        List<Region> regionList = new ArrayList<>();
        Region region = regionService.getRegionByCurrentLocation();
        if(region != null)
            regionList.add(region);
        return regionList;
    }

    @GetMapping("/regions/cache")
    public Map<String, Object> getCacheDetails() {
        return regionService.getCacheDetails();
    }
}
