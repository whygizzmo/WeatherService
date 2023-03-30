package com.mega.weatherservice.services;

import org.json.JSONObject;

import java.net.URL;
import java.util.List;

public interface WeatherService {
    String getUrlWeather();

    List<JSONObject> getFiveWeather(String jsonList);

    String toСelsius(String temp);
    //public List<JSONObject>
}
