package com.example.ForecastApp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.TypeConverters;

import com.example.ForecastApp.Database.convertWeatherType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {
    @SerializedName("dt")
    @Expose
    public long dateAndTime;

    @SerializedName("main")
    @Expose
    public Main main;

    @TypeConverters(convertWeatherType.class)
    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;

    @Embedded
    @SerializedName("clouds")
    @Expose
    public Clouds clouds;

    @Embedded
    @SerializedName("wind")
    @Expose
    public Wind wind;

    public Day() {
    }
}
