package tqs.airQuality.controller;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RequestBody;
import tqs.airQuality.model.Continent;
import tqs.airQuality.model.Country;
import tqs.airQuality.service.ContinentService;
import tqs.airQuality.service.CountryService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CountryControllerTest {

    private static Country c1;
    private static Country c2;
    private static Country c3;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private CountryController countryController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        c1 = new Country("Country1","ContinentA","Flag1");
        c2 = new Country("Country2","ContinentA", "Flag2");
        c3 = new Country("Country3","ContinentB","Flag3");
    }

    @Test
    public void whenEmptyTable_getAllCountriesTest() {
        List<Country> allCountries = new ArrayList<>();
        Mockito.when(countryService.getAllCountries()).thenReturn(allCountries);
        assertEquals(allCountries, countryController.getAllCountries());
        Mockito.verify(countryService, Mockito.times(1)).getAllCountries();
    }

    @Test
    public void whenNotEmptyTable_getAllCountriesTest() {
        List<Country> allCountries = new ArrayList<>();
        allCountries.add(c1);
        allCountries.add(c2);
        Mockito.when(countryService.getAllCountries()).thenReturn(allCountries);
        assertEquals(allCountries, countryController.getAllCountries());
        Mockito.verify(countryService, Mockito.times(1)).getAllCountries();
    }

    @Test
    public void whenEmptyTable_getCountriesByContinent() {
        List<Country> allCountries = new ArrayList<>();
        Mockito.when(countryService.getCountriesByContinent("SomeContinent")).thenReturn(allCountries);
        assertEquals(allCountries, countryController.getCountriesByContinent("SomeContinent"));
        Mockito.verify(countryService, Mockito.times(1)).getCountriesByContinent("SomeContinent");
    }

    @Test
    public void whenNotEmptyTable_getCountriesByContinent() {
        List<Country> allCountries = new ArrayList<>();
        allCountries.add(c1);
        allCountries.add(c2);
        Mockito.when(countryService.getCountriesByContinent("ContinentA")).thenReturn(allCountries);
        assertEquals(allCountries, countryController.getCountriesByContinent("ContinentA"));
        Mockito.verify(countryService, Mockito.times(1)).getCountriesByContinent("ContinentA");
    }

    @Test
    public void createCountries() {
        List<Country> allCountries = new ArrayList<>();
        allCountries.add(c1);
        allCountries.add(c2);
        countryController.createCountries(allCountries);
        Mockito.verify(countryService, Mockito.times(1)).createCountries(allCountries);
    }
}
