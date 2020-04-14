package tqs.airQuality.controller;

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
import tqs.airQuality.cache.Cache;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Region;
import tqs.airQuality.repository.ContinentRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ContinentControllerTest {

    private static Continent c1;
    private static Continent c2;
    private static Continent c3;


    @Mock
    private ContinentRepository continentRepository;

    @InjectMocks
    private ContinentController continentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        c1 = new Continent("EUROPE");
        c2 = new Continent("AFRICA");
        c3 = new Continent("OCEANIA");
    }

    @Test
    public void getAllContinentsNotInCache() {
        List<Continent> allContinents = new ArrayList<>();
        Mockito.when(continentRepository.findAll()).thenReturn(allContinents);
        assertEquals(continentController.getAllContinents(), allContinents);
        Mockito.verify(continentRepository, Mockito.times(1)).findAll();
    }

    @Test
    void createContinent() {
        continentController.createContinent(c1);
        Mockito.verify(continentRepository, Mockito.times(1)).save(c1);
    }

}
