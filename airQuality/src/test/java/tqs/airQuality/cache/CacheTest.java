package tqs.airQuality.cache;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    private Cache<String, Integer> cache;
    private int TIME_TO_LIVE = 3;
    private int TIMER = 5;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - Initialized CacheTest");
    }

    /**
     * Method to test the instance of a new cache object.
     * It checks if the "timeToLive" and "timer" arguments are greater than zero,
     * throwing an exception if not.
     */
    @Test
    void cacheInstanceTest() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                cache = new Cache(0, 0);
            }
        });
    }

    @Test
    void putTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        String key = "one";
        Integer value = 1;

        cache.put(key, value);
        assertEquals(value, cache.get(key));

        key = "two";
        value = 2;
        cache.put(key, value);
        assertEquals(value, cache.get(key));
    }

    @Test
    void getTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        String key = "one";
        Integer value = 1;

        cache.put(key, value);
        assertEquals(value, cache.get(key));
    }

    @Test
    void sizeTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        int SIZE = 3;
        for(int i = 1; i <= SIZE; i++) {
            cache.put("integer_" + i, i);
        }
        assertEquals(SIZE, cache.size());
    }

    @Test
    void removeTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        cache.put("one", 1);
        cache.put("two", 2);

        cache.remove("one");
        assertEquals(null, cache.get("one"));
        assertNotEquals(null, cache.get("two"));
    }


    @Test
    void objectExpiredTest() throws InterruptedException {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        cache.put("one", 1);
        Thread.sleep((TIMER + 5) * 1000);
        assertEquals(null, cache.get("one"));
    }

    @Test
    void objectNotExpiredTest() throws InterruptedException {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        cache.put("one", 1);
        Thread.sleep(TIME_TO_LIVE);
        assertEquals(1, cache.get("one"));
    }

    @Test
    void clean() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        int SIZE = 3;
        int requests = 0, hits = 0, misses = 0;
        for(int i = 1; i <= SIZE; i++) {
            cache.get("integer_" + i);
            requests++;
            misses ++;
            cache.put("integer_" + i, i);
            cache.get("integer_" + i);
            requests++;
            hits++;
        }
        assertEquals(SIZE, cache.size());
        assertEquals(requests, cache.getRequests());
        assertEquals(hits, cache.getHits());
        assertEquals(misses, cache.getMisses());


        cache.clean();
        requests = 0;
        hits = 0;
        misses = 0;
        assertEquals(0, cache.size());
        assertEquals(requests, cache.getRequests());
        assertEquals(hits, cache.getHits());
        assertEquals(misses, cache.getMisses());
    }

    @Test
    void requestsTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        cache.get("one");
        cache.put("one", 1);
        cache.get("one");

        assertEquals(2, cache.getRequests());
    }

    @Test
    void hitsTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        cache.put("one", 1);
        cache.get("one");

        assertEquals(1, cache.getHits());
    }

    @Test
    void missesTest() {
        cache = new Cache(TIME_TO_LIVE, TIMER);

        cache.get("one");
        cache.put("one", 1);

        assertEquals(1, cache.getMisses());
    }
    
}