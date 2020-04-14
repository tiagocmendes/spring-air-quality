package tqs.airQuality.service;

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
import org.springframework.boot.test.context.SpringBootTest;
import tqs.airQuality.model.Continent;
import tqs.airQuality.repository.ContinentRepository;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
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
    }

    @Test
    public void findAllTest_WhenNoRecord() {

        Mockito.when(continentRepository.findAll()).thenReturn(Arrays.asList());
        assertEquals(0, continentService.getAllContinents().size());
        Mockito.verify(continentRepository, Mockito.times(1)).findAll();

    }

}
