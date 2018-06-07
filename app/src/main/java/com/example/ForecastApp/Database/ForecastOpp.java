package com.example.ForecastApp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.ForecastApp.model.Forecast;

import io.reactivex.Maybe;

@Dao
public interface ForecastOpp {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForecasts(Forecast... forecasts);

    @Query("SELECT * FROM forecast")
    Maybe<Forecast> getForecast();
}
