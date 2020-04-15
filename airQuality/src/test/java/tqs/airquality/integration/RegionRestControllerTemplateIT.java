package tqs.airquality.integration;


import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tqs.airquality.model.Region;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegionRestControllerTemplateIT {

    private static Region r1;
    private static Region r2;
    private static Region r3;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeAll
    public static void init() throws JSONException {
        JSONObject pollutants = new JSONObject();
        pollutants.put("pm25", 100);
        pollutants.put("no2", 60);
        pollutants.put("o3", 30);
        r1 = new Region("Lisboa",(double) -40.0, (double) 8.0, "url", 10, "pm25", pollutants, "2020-04-15", "GMT+2");
        r2 = new Region("Porto",(double) -41.0, (double) 8.0, "url", 10, "pm25", pollutants, "2020-04-15", "GMT+2");
        r3 = new Region("Aveiro",(double) -42.0, (double) 8.0, "url", 10, "pm25", pollutants, "2020-04-15", "GMT+2");
    }

    @Test
    public void getRegionByNameTestIT() throws JSONException {
        String regionName = "lisboa";
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:" + port + "/regions/" + regionName, ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());

        Map<Object, Object> region = (LinkedHashMap) response.getBody().get(0);
        assertNotEquals(null, region.get("name"));
        assertNotEquals(null, region.get("latitude"));
        assertNotEquals(null, region.get("longitude"));
        assertNotEquals(null, region.get("aqi"));
        assertNotEquals(null, region.get("primaryPollutant"));
        assertNotEquals(null, region.get("pollutants"));
        assertNotEquals(null, region.get("time"));
        assertNotEquals(null, region.get("timeZone"));
    }


    @Test
    public void getUnknownRegionTestIT() throws JSONException {
        String unknownRegion = "unknown";
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:"+port+"/regions/" + unknownRegion, ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(0, response.getBody().size());
    }

    @Test
    public void getRegionByCurrentLocationTestIT() throws JSONException {
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:"+port+"/here", ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());

        Map<Object, Object> region = (LinkedHashMap) response.getBody().get(0);
        assertNotEquals(null, region.get("name"));
        assertNotEquals(null, region.get("latitude"));
        assertNotEquals(null, region.get("longitude"));
        assertNotEquals(null, region.get("aqi"));
        assertNotEquals(null, region.get("primaryPollutant"));
        assertNotEquals(null, region.get("pollutants"));
        assertNotEquals(null, region.get("time"));
        assertNotEquals(null, region.get("timeZone"));
    }

}
