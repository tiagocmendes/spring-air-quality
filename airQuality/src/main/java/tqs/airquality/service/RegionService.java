package tqs.airquality.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tqs.airquality.cache.Cache;
import tqs.airquality.http.AirQualityHttpClient;
import tqs.airquality.http.HttpClient;
import tqs.airquality.model.Region;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Service
@Transactional
public class RegionService {

    private long TIME_TO_LIVE = 120;
    private long TIMER = 60;
    private Cache<String, Region> regionCache = new Cache<>(TIME_TO_LIVE, TIMER);

    public Region getResponse(String uri) throws IOException, URISyntaxException {
        HttpClient httpClient = new AirQualityHttpClient();
        URIBuilder uriBuilder = new URIBuilder(uri);
        String response = httpClient.get(uriBuilder.build().toString());
        try {
            JSONObject jsonResponse = (JSONObject) new JSONObject(response);

            JSONObject data = (JSONObject) jsonResponse.get("data");

            // City
            JSONObject city = (JSONObject) data.get("city");

            String cityName = (String) city.get("name");

            JSONArray geo = new JSONArray(city.get("geo").toString());
            Double latitude = Double.parseDouble(geo.get(0).toString());
            Double longitude = Double.parseDouble(geo.get(1).toString());

            String url = (String) city.get("url");


            // Air Quality Indicator
            Integer aqi = (Integer) data.get("aqi");



            // Pollutants
            String primaryPollutant = (String) data.get("dominentpol");
            JSONObject pollutants = (JSONObject) data.get("iaqi");


            // Time
            JSONObject time = (JSONObject) data.get("time");
            String timestamp = (String) time.get("s");
            String timezone = (String) time.get("tz");

            Region region = new Region(cityName, latitude, longitude, url, aqi, primaryPollutant, pollutants, timestamp, timezone);

            return region;
        }
        catch (Exception e) {
            return null;
        }
    }

    public Region getRegionByName(String name) throws IOException, URISyntaxException {

        Region region = regionCache.get(name.toLowerCase());

        if(region == null) {
            region = getResponse("https://api.waqi.info/feed/" + name);

            if(region != null)
                regionCache.put(name.toLowerCase(), region);
        }

        return region;
    }

    public Region getRegionByCurrentLocation() throws IOException, URISyntaxException {

        String currentLocation = "currentLocation";
        Region region = regionCache.get(currentLocation);

        if(region == null) {
            region = getResponse("https://api.waqi.info/feed/here");

            if(region != null)
                regionCache.put(currentLocation, region);
        }

        return region;
    }

    public Map<String, Object> getCacheDetails() {
        return regionCache.getDetails();
    }
}
