package com.mega.weatherservice.models;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    String temp;
    String feelsLike;
    String humidity;
    String weath;
    String windSpeed;
    LocalDate date;
}
