package com.example.ForecastApp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    @Expose
    public double speed;

    @SerializedName("deg")
    @Expose
    public double deg;

    public Wind() {
    }
}
