package com.example.ForecastApp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("all")
    @Expose
    public int all;

    public Clouds() {
    }
}
