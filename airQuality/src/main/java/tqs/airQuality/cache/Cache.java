package tqs.airQuality.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Cache<K, T> {

    private long timeToLive;
    private Map<K, CacheObject> data;
    private int requests;
    private int hits;
    private int misses;

    protected class CacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CacheObject(T value) {
            this.value = value;
        }

    }

    public Cache(long timeToLive, final long timer) {

        if(timeToLive <= 0 || timer <= 0)
            throw new IllegalArgumentException("Time to live and timer must be greater than 0!");

        this.timeToLive = timeToLive * 1000;
        this.data = new HashMap<>();


        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(timer * 1000);
                    } catch (InterruptedException ex) {}

                    refresh();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void put(K key, T value) {
        synchronized (data) {
            System.out.println("Putting new item!");
            data.put(key, new CacheObject(value));
        }
    }

    public T get(K key) {
        synchronized (data) {
            this.requests++;

            CacheObject cacheObject = data.get(key);
            if(cacheObject == null) {
                this.misses++;
                return null;
            }
            System.out.println("Retrieving cached item!");
            this.hits++;
            cacheObject.lastAccessed = System.currentTimeMillis();
            return cacheObject.value;
        }
    }

    public int size() {
        synchronized (data) {
            return data.size();
        }
    }

    public void remove(String key) {
        synchronized (data) {
            data.remove(key);
        }
    }

    public void refresh() {

        long now = System.currentTimeMillis();

        List<K> expiredObjects = new ArrayList<>();

        synchronized (data) {

            for(K key : data.keySet()) {

                CacheObject cacheObject = data.get(key);

                if(cacheObject != null && now > (timeToLive + cacheObject.lastAccessed)) {
                    expiredObjects.add(key);
                }
            }
        }

        for(K key: expiredObjects) {
            synchronized (data) {
                data.remove(key);
            }

            Thread.yield();
        }
    }



}
