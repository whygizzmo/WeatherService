package com.mega.weatherservice;

import com.mega.weatherservice.models.Weather;
import com.mega.weatherservice.panel.WeatherPanel;
import com.mega.weatherservice.services.WeatherService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private final WeatherService weatherService;

    //private Grid<Weather> grid = new Grid<>(Weather.class);


    public MainView(WeatherService weatherService) {
        this.weatherService = weatherService;

//        Label label = new Label("Прогноз погоды");
//        label.getElement().getStyle().set("text-align", "center");


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








      /*  add(label);
        add(grid);

        grid.setItems(weatherList);
*/

      /*  add("Температура : " + (weatherService.toСelsius(weatherList.get(0).getTemp()) + " °C") + "\t" +
                "Ощущается " + (weatherService.toСelsius(weatherList.get(0).getFeelsLike())) + "\t" +
                "Влажность " + (weatherList.get(0).getHumidity()) + "\n" +
                "Погода " + (weatherList.get(0).getWeath()) + "\n" +
                "Скорость ветра " + (weatherList.get(0).getWindSpeed()) + "\n" +
                "День " + (weatherList.get(0).getDate().toString()));
        add("Температура : " + (weatherService.toСelsius(weatherList.get(1).getTemp()) + " °C"));
        add("Температура : " + (weatherService.toСelsius(weatherList.get(2).getTemp()) + " °C"));*/


       /* VerticalLayout todosList = new VerticalLayout();
        TextField taskField = new TextField();
        Button addButton = new Button("Add");
        addButton.addClickListener(click -> {
            Checkbox checkbox = new Checkbox(taskField.getValue());
            todosList.add(checkbox);
        });
        addButton.addClickShortcut(Key.ENTER);

        Label label = new Label("Заголовок");
        label.getElement().getStyle().set("text-align", "center");

        *//*H1 h1 = new H1("Weather forecast");
        h1.getStyle().set("text-align", "center");*//*
        add(new H1(label),
                todosList,
                new HorizontalLayout(
                        taskField,
                        addButton
                )

        );*/
    }
}
