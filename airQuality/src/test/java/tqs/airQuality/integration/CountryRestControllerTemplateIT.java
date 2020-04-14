package tqs.airQuality.integration;

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
import tqs.airQuality.model.Country;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryRestControllerTemplateIT {

    private static Country c1;
    private static Country c2;
    private static Country c3;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeAll
    public static void init() {
        c1 = new Country("Country1", "ContinentA", "Flag1");
        c2 = new Country("Country2", "ContinentA", "Flag2");
        c3 = new Country("Country3", "ContinentB", "Flag3");
    }

    @Test
    public void getAllCountriesTestIT() {
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:" + port + "/countries", ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());
    }

    @Test
    public void whenKnownContinent_getCountriesByContinentTestIT() {
        String continent = "EUROPE";
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:" + port + "/countries/" + continent, ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertNotEquals(0, response.getBody().size());
    }

    @Test
    public void whenUnknownContinent_GetEmptyArrayTestIT() {
        String continent = "Unknown";
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:" + port + "/countries/" + continent, ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(0, response.getBody().size());
    }

}
