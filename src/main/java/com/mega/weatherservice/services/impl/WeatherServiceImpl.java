package com.mega.weatherservice.services.impl;

import com.mega.weatherservice.models.Weather;
import com.mega.weatherservice.services.WeatherService;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Weather> getFiveWeather(String jsonList) {
        List<JSONObject> jsonObjects = new ArrayList<>();
        List<Weather>weatherList = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonList.substring(41));

        LocalDate date = LocalDate.now();
        int j = 0;
        String test = " ";
        for (int i = 0; i < 5; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(j);

            if (((String) jsonObject.get("dt_txt")).substring(8, 10).equals(test)) {
                j++;
                i--;
                continue;
            }

            if (((String) jsonObject.get("dt_txt")).substring(8, 10).equals(date.getDayOfMonth() < 10 ? "0" + (date.getDayOfMonth()) : Integer.toString(date.getDayOfMonth()))) {
                test = (Integer.toString(date.getDayOfMonth()));
                JSONObject jsonObjectNext = (JSONObject) jsonObject.get("main");
                jsonObjects.add(jsonObjectNext);
                date = date.plusDays(1L);
                Weather weather = new Weather();

                weather.setTemp(jsonObjectNext.get("temp").toString());
                weather.setFeelsLike(jsonObjectNext.get("feels_like").toString());
                weather.setHumidity(jsonObjectNext.get("humidity").toString());

                JSONArray jsonArrForWeather = (JSONArray) jsonObject.get("weather");
                jsonObjectNext = jsonArrForWeather.getJSONObject(0);
                weather.setWeath(jsonObjectNext.get("main").toString());

                jsonObjectNext = (JSONObject) jsonObject.get("wind");
                weather.setWindSpeed(jsonObjectNext.get("speed").toString());

                String dateFromJson = jsonObject.get("dt_txt").toString();
                weather.setDate(LocalDate.of((Integer.valueOf(dateFromJson.substring(0,4))),
                                             (Integer.valueOf(dateFromJson.substring(5,7)))<10?(Integer.valueOf(dateFromJson.substring(6,7))):(Integer.valueOf(dateFromJson.substring(5,7))),
                                             (Integer.valueOf(dateFromJson.substring(8,10)))<10?(Integer.valueOf(dateFromJson.substring(9,10))):(Integer.valueOf(dateFromJson.substring(8,10)))));

                weatherList.add(weather);



            } else {
                i--;
            }

            j++;
        }

        return weatherList;
    }

    @Override
    public String toСelsius(String temp) {
        double tempInKelv = Double.parseDouble(temp);
        double tempCels = tempInKelv - 273.15;

        return temp = String.format("%.0f", tempCels);

    }
}
