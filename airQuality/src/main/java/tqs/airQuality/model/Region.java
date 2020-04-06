package tqs.airQuality.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private Double aqi; // Air Quality Indicator

    @NotBlank
    private String primaryPollutant;

    private Double pollutantH;
    private Double pollutantNO2;
    private Double pollutantO3;
    private Double pollutantP;
    private Double pollutantPM10;
    private Double pollutantPM25;
    private Double pollutantSO2;

    @NotBlank
    private String time;

    @NotBlank
    private String timeZone;

    /* Constructors */

    public Region() {
        super();
    }

    public Region(Long id, String name, Double latitude, Double longitude, String url, Double aqi, String primaryPollutant, Double pollutantH, Double pollutantNO2, Double pollutantO3, Double pollutantP, Double pollutantPM10, Double pollutantPM25, Double pollutantSO2, String time, String timeZone) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
        this.aqi = aqi;
        this.primaryPollutant = primaryPollutant;
        this.pollutantH = pollutantH;
        this.pollutantNO2 = pollutantNO2;
        this.pollutantO3 = pollutantO3;
        this.pollutantP = pollutantP;
        this.pollutantPM10 = pollutantPM10;
        this.pollutantPM25 = pollutantPM25;
        this.pollutantSO2 = pollutantSO2;
        this.time = time;
        this.timeZone = timeZone;
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

    public Double getAqi() {
        return aqi;
    }

    public void setAqi(Double aqi) {
        this.aqi = aqi;
    }

    public String getPrimaryPollutant() {
        return primaryPollutant;
    }

    public void setPrimaryPollutant(String primaryPollutant) {
        this.primaryPollutant = primaryPollutant;
    }

    public Double getPollutantH() {
        return pollutantH;
    }

    public void setPollutantH(Double pollutantH) {
        this.pollutantH = pollutantH;
    }

    public Double getPollutantNO2() {
        return pollutantNO2;
    }

    public void setPollutantNO2(Double pollutantNO2) {
        this.pollutantNO2 = pollutantNO2;
    }

    public Double getPollutantO3() {
        return pollutantO3;
    }

    public void setPollutantO3(Double pollutantO3) {
        this.pollutantO3 = pollutantO3;
    }

    public Double getPollutantP() {
        return pollutantP;
    }

    public void setPollutantP(Double pollutantP) {
        this.pollutantP = pollutantP;
    }

    public Double getPollutantPM10() {
        return pollutantPM10;
    }

    public void setPollutantPM10(Double pollutantPM10) {
        this.pollutantPM10 = pollutantPM10;
    }

    public Double getPollutantPM25() {
        return pollutantPM25;
    }

    public void setPollutantPM25(Double pollutantPM25) {
        this.pollutantPM25 = pollutantPM25;
    }

    public Double getPollutantSO2() {
        return pollutantSO2;
    }

    public void setPollutantSO2(Double pollutantSO2) {
        this.pollutantSO2 = pollutantSO2;
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
}
