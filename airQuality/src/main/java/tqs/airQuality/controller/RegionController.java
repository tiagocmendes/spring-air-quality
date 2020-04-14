package tqs.airQuality.controller;

import jdk.nashorn.api.scripting.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import tqs.airQuality.exception.RegionNotFoundException;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.RegionRepository;
import tqs.airQuality.service.RegionService;
import tqs.airQuality.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
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
         // System.out.println(request.getRemoteAddr());
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
