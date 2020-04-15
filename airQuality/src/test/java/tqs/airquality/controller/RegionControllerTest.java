package tqs.airquality.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tqs.airquality.model.Region;
import tqs.airquality.service.RegionService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RegionControllerTest {

    private static Region r1;
    private static Region r2;
    private static Region r3;

    @Mock
    private RegionService regionService;

    @InjectMocks
    private RegionController regionController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        r1 = new Region("Region1",(double) -40.0, (double) 8.0, "url", 10, "co2", new JSONObject(), "2020-04-15", "GMT+2");
    }

    @Test
    public void getRegionByNameInCache() throws IOException, URISyntaxException {
        List<Region> region = new ArrayList<>();
        region.add(r1);
        Mockito.when(regionService.getRegionByName("Region1")).thenReturn(r1);
        assertEquals(regionController.getRegionByName("Region1"), region);
        Mockito.verify(regionService, Mockito.times(1)).getRegionByName("Region1");
    }

    @Test
    public void getRegionByCurrentLocationInCache() throws IOException, URISyntaxException {
        List<Region> region = new ArrayList<>();
        region.add(r1);
        Mockito.when(regionService.getRegionByCurrentLocation()).thenReturn(r1);
        assertEquals(regionController.getRegionByCurrentLocation(), region);
        Mockito.verify(regionService, Mockito.times(1)).getRegionByCurrentLocation();
    }

    @Test
    public void getRegionByNameNotInCache() throws IOException, URISyntaxException {
        List<Region> region = new ArrayList<>();
        Mockito.when(regionService.getRegionByName("Region1")).thenReturn(null);
        assertEquals(regionController.getRegionByName("Region1"), region);
        Mockito.verify(regionService, Mockito.times(1)).getRegionByName("Region1");
    }


    @Test
    public void getRegionByCurrentLocationNotInCache() throws IOException, URISyntaxException {
        List<Region> region = new ArrayList<>();
        Mockito.when(regionService.getRegionByCurrentLocation()).thenReturn(null);
        assertEquals(regionController.getRegionByCurrentLocation(), region);
        Mockito.verify(regionService, Mockito.times(1)).getRegionByCurrentLocation();
    }

    @Test
    public void getCacheDetails() {

        Map<String, Object> cacheDetails = new HashMap<>();
        cacheDetails.put("requests", 2);
        cacheDetails.put("hits", 1);
        cacheDetails.put("misses",1 );
        cacheDetails.put("timeToLive", 60);
        cacheDetails.put("timer", 65);
        cacheDetails.put("lastRefresh", System.currentTimeMillis());
        cacheDetails.put("data", new HashMap<>());

        Mockito.when(regionService.getCacheDetails()).thenReturn(cacheDetails);
        assertEquals(regionController.getCacheDetails(), cacheDetails);
        Mockito.verify(regionService, Mockito.times(1)).getCacheDetails();
    }


}
