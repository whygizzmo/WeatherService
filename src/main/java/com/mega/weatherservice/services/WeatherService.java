package com.mega.weatherservice.services;

import com.mega.weatherservice.models.Weather;
import org.json.JSONObject;

import java.net.URL;
import java.util.List;

public interface WeatherService {
    String getUrlWeather();

    List<Weather> getFiveWeather(String jsonList);

    String toСelsius(String temp);
    //public List<JSONObject>
}
