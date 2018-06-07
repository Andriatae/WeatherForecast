package com.example.ForecastApp.application;

import android.app.Application;

import com.example.ForecastApp.Dagger_App.AppComponent;
import com.example.ForecastApp.Dagger_App.AppModule;
import com.example.ForecastApp.Dagger_App.DaggerAppComponent;

import net.danlew.android.joda.JodaTimeAndroid;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                            .appModule(new AppModule(this))
                            .build();
        JodaTimeAndroid.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
