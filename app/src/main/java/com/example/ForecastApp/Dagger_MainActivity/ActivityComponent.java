package com.example.ForecastApp.Dagger_MainActivity;

import com.example.ForecastApp.Dagger_App.AppComponent;
import com.example.ForecastApp.Activities.HomeActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(HomeActivity homeActivity);
}
