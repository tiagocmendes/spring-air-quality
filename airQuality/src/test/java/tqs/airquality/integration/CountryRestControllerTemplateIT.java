package tqs.airquality.integration;

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
import tqs.airquality.model.Country;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void createCountriesTestIT() {

        ResponseEntity<ArrayList> postResponse = this.restTemplate.postForEntity("http://127.0.0.1:" + port + "/countries", Arrays.asList(c1,c2), ArrayList.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertNotEquals(null, postResponse.getBody());
        assertEquals(ArrayList.class, postResponse.getBody().getClass());
        Map<Object, Object>  firstCountry = (LinkedHashMap) postResponse.getBody().get(0);
        Map<Object, Object>  secondCountry = (LinkedHashMap) postResponse.getBody().get(1);
        assertEquals(c1.getName(), firstCountry.get("name"));
        assertEquals(c2.getName(), secondCountry.get("name"));
    }

}
