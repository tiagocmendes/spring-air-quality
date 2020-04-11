package tqs.airQuality.cache;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    private Cache cache;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @Test
    void timeToLiveTest() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                cache = new Cache(0, 0);
            }
        });
    }


}