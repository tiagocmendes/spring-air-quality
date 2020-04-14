package tqs.airQuality.integration;

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
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContinentRestControllerTemplateIT {

    private static Continent c1;
    private static Continent c2;
    private static Continent c3;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        c1 = new Continent("ContinentA");
        c2 = new Continent("ContinentB");
        c3 = new Continent("ContinentC");
    }

    @Test
    public void getAllCountriesTestIT() {
        ResponseEntity<ArrayList> response = this.restTemplate.getForEntity("http://127.0.0.1:" + port + "/continents", ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(null, response.getBody());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertNotEquals(6, response.getBody().get(0));
    }
}
