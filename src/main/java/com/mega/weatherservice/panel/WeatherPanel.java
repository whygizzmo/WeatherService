package com.mega.weatherservice.panel;

import com.mega.weatherservice.models.Weather;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class WeatherPanel extends VerticalLayout {
    public WeatherPanel(Weather weather) {

        Label dateLabel = new Label(weather.getDate().toString());
        Label tempLabel = new Label("Температура: " + weather.getTemp() + " °C");
        Label feelsLikeLabel = new Label("Ощущается как: " + weather.getFeelsLike() + " °C");
        Label humidityLabel = new Label("Влажность: " + weather.getHumidity() + "%");
        Label weathLabel = new Label("Погода: " + weather.getWeath());
        Label windSpeedLabel = new Label("Скорость ветра: " + weather.getWindSpeed() + " м/c");


        add(dateLabel, tempLabel, feelsLikeLabel, humidityLabel, weathLabel, windSpeedLabel);
        setWidth("200px");
        setPadding(true);
        setMargin(true);
        getElement().getStyle().set("border", "1px solid gray");
        getElement().getStyle().set("border-radius", "5px");
}
}
