package com.example.ForecastApp.Database;

import android.arch.persistence.room.TypeConverter;

import com.example.ForecastApp.model.Day;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class convertDay {

    @TypeConverter
    public static String ltString(List<Day> days) {
        return new Gson().toJson(days);
    }


    @TypeConverter
    public static List<Day> stringtl(String data) {
        final Gson gson= new Gson();
        if (data == null) {
            return Collections.emptyList();
        }
        Type type = new TypeToken<List<Day>>() {}.getType();
        return gson.fromJson(data, type);
    }


}
