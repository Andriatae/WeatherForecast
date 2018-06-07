package com.example.ForecastApp.Database;

import android.arch.persistence.room.TypeConverter;

import com.example.ForecastApp.model.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class convertWeatherType {
    @TypeConverter
    public static List<Weather> stringToListOfWeather(String data) {
        final Gson gson= new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Weather>>() {}.getType();
        return gson.fromJson(data, type);
    }

    @TypeConverter
    public static String listOfWeatherToString(List<Weather> weatherList) {
        return new Gson().toJson(weatherList);
    }
}
