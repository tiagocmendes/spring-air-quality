package tqs.airQuality.service;

import tqs.airQuality.cache.Cache;
import tqs.airQuality.http.AirQualityHttpClient;
import tqs.airQuality.http.HttpClient;
import tqs.airQuality.model.Region;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.http.ParseException;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

public class RegionService {

    private static Cache<String, Region> regionCache = new Cache<>(60, 60);

    public static Region getResponse(String uri) throws IOException, URISyntaxException {
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

    public static Region getRegionByName(String name) throws IOException, URISyntaxException {

        Region region = regionCache.get(name);

        if(region == null) {
            region = getResponse("https://api.waqi.info/feed/" + name);
            regionCache.put(name, region);
        }

        return region;
    }

    public static Region getRegionByCurrentLocation() throws IOException, URISyntaxException {
        return getResponse("https://api.waqi.info/feed/here");
    }
}
