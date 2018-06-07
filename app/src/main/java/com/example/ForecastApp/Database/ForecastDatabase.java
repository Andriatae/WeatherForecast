package com.example.ForecastApp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ForecastApp.model.Forecast;

@Database(entities = {Forecast.class}, version = 1)
public abstract class ForecastDatabase extends RoomDatabase{
    public abstract ForecastOpp forecastDao();
}
