package com.mega.weatherservice;

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
       List<JSONObject>jsonObjectList = weatherService.getFiveWeather(weatherService.getUrlWeather());

        add("Температура : "+ (weatherService.toСelsius(jsonObjectList.get(0).get("temp").toString()))+" °C");
        add("Температура : "+ (weatherService.toСelsius(jsonObjectList.get(1).get("temp").toString()))+" °C");
        add("Температура : "+ (weatherService.toСelsius(jsonObjectList.get(2).get("temp").toString()))+" °C");


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
