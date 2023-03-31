package com.mega.weatherservice;

import com.mega.weatherservice.models.Weather;
import com.mega.weatherservice.services.WeatherService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.json.JSONObject;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

           private final  WeatherService weatherService;



    public MainView(WeatherService weatherService) {
        this.weatherService = weatherService;

        //  List<JSONObject> weathers = weatherService.getFiveWeather();
        //weatherService.getFiveWeather(weatherService.getUrlWeather());
       List<Weather>weatherList = weatherService.getFiveWeather(weatherService.getUrlWeather());

        add("Температура : "+ (weatherService.toСelsius(weatherList.get(0).getTemp())+" °C") +"\t"+
            "Ощущаетс "+ (weatherService.toСelsius(weatherList.get(0).getFeelsLike())) +"\t"+
            "Влажность "+(weatherList.get(0).getHumidity()) +"\n"+
            "Погода "+(weatherList.get(0).getWeath()) +"\n"+
            "Скорость ветра "+(weatherList.get(0).getWindSpeed()) +"\n"+
            "День "+(weatherList.get(0).getDate().toString()));
        add("Температура : "+ (weatherService.toСelsius(weatherList.get(1).getTemp())+" °C"));
        add("Температура : "+ (weatherService.toСelsius(weatherList.get(2).getTemp())+" °C"));


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
