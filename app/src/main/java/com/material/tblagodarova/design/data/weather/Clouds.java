package com.material.tblagodarova.design.data.weather;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tblagodarova on 7/17/15.
 */
public class Clouds {private Integer all;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     *
     * @param all
     * The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}