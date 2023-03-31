package com.mega.weatherservice.services.impl;

import com.mega.weatherservice.services.WeatherService;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Override
    public String getUrlWeather() {
        String urlForWeather = "https://api.openweathermap.org/data/2.5/forecast?lat=42.87&lon=74.59&appid=b614bc6d7a775f3a6d80c12f669f2e0b";
        String jsonListWeather = "";

        Request request = new Request.Builder()
                .url(urlForWeather)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();

        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();

            jsonListWeather = response.body().string();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonListWeather;

    }

    @Override
    public List<JSONObject> getFiveWeather(String jsonList) {
        List<JSONObject> jsonObjects = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonList.substring(41));

        LocalDate date = LocalDate.now();
        int j = 0;
        String test = " ";
        for (int i = 0; i < 5; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(j);

            if (((String) jsonObject.get("dt_txt")).substring(8,10).equals(test)){
                j++;
                i--;
                continue;
            }

            if (((String) jsonObject.get("dt_txt")).substring(8,10).equals( date.getDayOfMonth()<10?"0"+(date.getDayOfMonth()):Integer.toString(date.getDayOfMonth()))) {
                test = (Integer.toString(date.getDayOfMonth()));
                JSONObject jsonObjectNext = (JSONObject) jsonObject.get("main");
                jsonObjects.add(jsonObjectNext);
               date = date.plusDays(1L);


            }
            else{
                i--;}

            j++;
        }

        return jsonObjects;
    }

    @Override
    public String toСelsius(String temp) {
        double tempInKelv = Double.parseDouble(temp);
        double tempCels = tempInKelv-273.15;

        return temp = String.valueOf(tempCels);

    }
}
