package tqs.airquality.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tqs.airquality.converter.JSONObjectConverter;

import java.io.IOException;
import java.util.*;

@Entity
@Table(name = "regions")
public class Region {

    /* Properties */
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String url;

    @NotNull
    private Integer aqi; // Air Quality Indicator

    @NotBlank
    private String primaryPollutant;

    @NotBlank
    private String time;

    @NotBlank
    private String timeZone;

    @NotNull
    @Column(columnDefinition = "TEXT")
    @Convert(converter = JSONObjectConverter.class)
    @JsonIgnore
    private JSONObject pollutants;

    /* Constructors */

    public Region() {
        super();
    }

    public Region(String name) {
        this.name = name + " not found!";
    }

    public Region(String name, Double latitude, Double longitude, String url, Integer aqi, String primaryPollutant, JSONObject pollutants ,String time, String timeZone) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
        this.aqi = aqi;
        this.primaryPollutant = primaryPollutant;
        this.pollutants = pollutants;
        this.time = time;
        this.timeZone = timeZone;
    }

    @JsonProperty("pollutants")
    public Map<String, Object> getAsJsonString() throws IOException, JSONException {
        return toMap(pollutants);
    }

    public Map<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }


    /* Getters & Setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return Objects.equals(id, region.id) &&
                Objects.equals(name, region.name) &&
                Objects.equals(latitude, region.latitude) &&
                Objects.equals(longitude, region.longitude) &&
                Objects.equals(url, region.url) &&
                Objects.equals(aqi, region.aqi) &&
                Objects.equals(primaryPollutant, region.primaryPollutant) &&
                Objects.equals(time, region.time) &&
                Objects.equals(timeZone, region.timeZone) &&
                Objects.equals(pollutants, region.pollutants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, latitude, longitude, url, aqi, primaryPollutant, time, timeZone, pollutants);
    }
}
