package tqs.airQuality.exception;

public class RegionNotFoundException extends Exception {

    private long region_id;

    public RegionNotFoundException(long region_id) {
        super(String.format("Region with id '%s' not found.", region_id));
    }
}
