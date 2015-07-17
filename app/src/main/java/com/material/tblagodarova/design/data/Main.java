package com.material.tblagodarova.design.data;

/**
 * Created by tblagodarova on 7/17/15.
 */
import java.util.HashMap;
import java.util.Map;
public class Main {

    private Float temp;
    private Integer pressure;
    private Integer humidity;
    private Float tempMin;
    private Float tempMax;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The temp
     */
    public Float getTemp() {
        return temp;
    }

    /**
     *
     * @param temp
     * The temp
     */
    public void setTemp(Float temp) {
        this.temp = temp;
    }

    /**
     *
     * @return
     * The pressure
     */
    public Integer getPressure() {
        return pressure;
    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The tempMin
     */
    public Float getTempMin() {
        return tempMin;
    }

    /**
     *
     * @param tempMin
     * The temp_min
     */
    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    /**
     *
     * @return
     * The tempMax
     */
    public Float getTempMax() {
        return tempMax;
    }

    /**
     *
     * @param tempMax
     * The temp_max
     */
    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}