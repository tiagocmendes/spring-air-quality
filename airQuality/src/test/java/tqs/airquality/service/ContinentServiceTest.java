package tqs.airquality.service;

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
import tqs.airquality.model.Continent;
import tqs.airquality.repository.ContinentRepository;

import java.io.IOException;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ContinentServiceTest {

    private static Continent c1;
    private static Continent c2;
    private static Continent c3;

    @Mock
    private ContinentRepository continentRepository;

    @InjectMocks
    private ContinentService continentService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        System.out.println("@BeforeAll - ContinentServiceTest initiated");
        c1 = new Continent("EUROPE");
        c2 = new Continent("AMERICA");
        c3 = new Continent("AFRICA");
    }


    @Test
    public void findAll_WhenRecordsTest() throws IOException {
        Mockito.when(continentRepository.findAll()).thenReturn(Arrays.asList(c1,c2,c3));
        assertEquals(Arrays.asList(c1,c2,c3), continentService.getAllContinents());
        assertEquals(3, continentService.getAllContinents().size());
        Mockito.verify(continentRepository, Mockito.times(2)).findAll();
    }

    @Test
    public void createNewContinentTest() {
        Mockito.when(continentRepository.save(c1)).thenReturn(c1);
        assertEquals(c1, continentService.createContinent(c1));
        Mockito.verify(continentRepository, Mockito.times(1)).save(c1);
    }

}
