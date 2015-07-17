package com.material.tblagodarova.design.ui.network;

import com.material.tblagodarova.design.data.WeatherJson;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by tblagodarova on 7/17/15.
 */
@Rest(rootUrl = "http://api.openweathermap.org", converters = {GsonHttpMessageConverter.class})

public interface RestClientManager extends RestClientErrorHandling {
    @Get("/data/2.5/weather?q={city},{locale}")
    WeatherJson getWeather(String city, String locale);
}
