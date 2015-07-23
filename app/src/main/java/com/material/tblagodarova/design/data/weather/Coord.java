package com.material.tblagodarova.design.data.weather;

/**
 * Created by tblagodarova on 7/17/15.
 */
import java.util.HashMap;
import java.util.Map;

public class Coord {

    private Float lon;
    private Float lat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The lon
     */
    public Float getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(Float lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
