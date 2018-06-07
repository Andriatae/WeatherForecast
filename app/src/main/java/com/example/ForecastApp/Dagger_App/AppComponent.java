package com.example.ForecastApp.Dagger_App;

import com.example.ForecastApp.Database.ForecastDatabase;
import com.example.ForecastApp.Network.ForecastService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ForecastService forecastService();
    ForecastDatabase forecastDatabase();
}
