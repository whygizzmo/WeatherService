package com.mega.weatherservice.views;

import com.mega.weatherservice.models.Weather;
import com.mega.weatherservice.panel.WeatherPanel;
import com.mega.weatherservice.services.WeatherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RoutePrefix;
import jakarta.annotation.security.PermitAll;
import org.json.JSONObject;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Route("")
@PermitAll
public class MainView extends VerticalLayout {

    private final WeatherService weatherService;


    public MainView(WeatherService weatherService) {
        this.weatherService = weatherService;

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        List<Weather> weatherList = weatherService.getFiveWeather(weatherService.getUrlWeather());

        for (Weather weather : weatherList) {
            weather.setTemp(weatherService.toСelsius(weather.getTemp()));
            weather.setFeelsLike(weatherService.toСelsius(weather.getFeelsLike()));
            WeatherPanel panel = new WeatherPanel(weather);
            horizontalLayout.add(panel);
        }

        Label titleLabel = new Label("Прогноз погоды на 5 дней г.Бишкек");
        titleLabel.getElement().getStyle().set("font-size", "24px");
        titleLabel.getElement().getStyle().set("font-weight", "center");
        add(titleLabel, horizontalLayout);

    }
}
