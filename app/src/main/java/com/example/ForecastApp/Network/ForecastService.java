package com.example.ForecastApp.Network;

import com.example.ForecastApp.DataBank.Constants;
import com.example.ForecastApp.model.Forecast;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ForecastService {

    @GET(Constants.FIVE_FORECAST)
    Observable<Forecast> FiveDayForecast(@Query("id") String id, @Query("APPID") String appId);

}
