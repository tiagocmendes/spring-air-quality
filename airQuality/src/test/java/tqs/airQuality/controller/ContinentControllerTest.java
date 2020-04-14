package tqs.airQuality.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.airQuality.model.Continent;
import tqs.airQuality.service.ContinentService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ContinentControllerTest {

    private static Continent c1;
    private static Continent c2;
    private static Continent c3;


    @Mock
    private ContinentService continentService;

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
    public void whenEmptyTable_getAllContinentsTest() {
        List<Continent> allContinents = new ArrayList<>();
        Mockito.when(continentService.getAllContinents()).thenReturn(allContinents);
        assertEquals(continentController.getAllContinents(), allContinents);
        Mockito.verify(continentService, Mockito.times(1)).getAllContinents();
    }

    @Test
    public void whenNotEmptyTable_getAllContinentsTest() {
        List<Continent> allContinents = new ArrayList<>();
        allContinents.add(c1);
        allContinents.add(c2);
        Mockito.when(continentService.getAllContinents()).thenReturn(allContinents);
        assertEquals(allContinents, continentController.getAllContinents());
        Mockito.verify(continentService, Mockito.times(1)).getAllContinents();
    }

    @Test
    void createContinent() {
        continentController.createContinent(c1);
        Mockito.verify(continentService, Mockito.times(1)).createContinent(c1);
    }

}
