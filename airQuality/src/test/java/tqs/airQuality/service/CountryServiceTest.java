package tqs.airQuality.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import tqs.airQuality.model.Country;
import tqs.airQuality.repository.CountryRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    private static Country c1;
    private static Country c2;
    private static Country c3;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        System.out.println("@BeforeAll - CountryServiceTest initiated");
        c1 = new Country("Country1", "ContinentA", "Flag1");
        c2 = new Country("Country2", "ContinentA", "Flag2");
        c3 = new Country("Country3", "ContinentB", "Flag3");
    }

    
    @Test
    public void findAll_WhenRecordsTest() throws IOException {
        Mockito.when(countryRepository.findAll()).thenReturn(Arrays.asList(c1,c2,c3));
        assertEquals(Arrays.asList(c1,c2,c3), countryService.getAllCountries());
        assertEquals(3, countryService.getAllCountries().size());
        Mockito.verify(countryRepository, Mockito.times(2)).findAll();
    }

    @Test
    public void createCountriesTest() {
        Mockito.when(countryRepository.saveAll(Arrays.asList(c1,c2,c3))).thenReturn(Arrays.asList(c1,c2,c3));
        assertEquals(Arrays.asList(c1,c2,c3), countryService.createCountries(Arrays.asList(c1,c2,c3)));
        Mockito.verify(countryRepository, Mockito.times(1)).saveAll(Arrays.asList(c1,c2,c3));
    }

    @Test
    public void findCountriesByName_WhenRecordsTest() throws IOException {
        Mockito.when(countryRepository.findAll()).thenReturn(Arrays.asList(c1,c2,c3));
        assertEquals(Arrays.asList(c1,c2), countryService.getCountriesByContinent("ContinentA"));
        Mockito.verify(countryRepository, Mockito.times(1)).findAll();
    }

}
