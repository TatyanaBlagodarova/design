package com.material.tblagodarova.design.data.weather;

/**
 * Created by tblagodarova on 7/17/15.
 */
import java.util.HashMap;
import java.util.Map;
public class Wind {

    private Float speed;
    private Integer deg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The speed
     */
    public Float getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The deg
     */
    public Integer getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     * The deg
     */
    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
