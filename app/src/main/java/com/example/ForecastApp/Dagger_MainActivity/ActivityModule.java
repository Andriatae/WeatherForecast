package com.example.ForecastApp.Dagger_MainActivity;

import com.example.ForecastApp.Database.ForecastDatabase;
import com.example.ForecastApp.mvp.ActivityContract;
import com.example.ForecastApp.mvp.ActivityPresnter;
import com.example.ForecastApp.Network.ForecastService;

import dagger.Module;
import dagger.Provides;
@Module
public class ActivityModule {

    private final ActivityContract.View homeView;

    public ActivityModule(ActivityContract.View homeView) {
        this.homeView = homeView;
    }

    @Provides
    @ActivityScope
    public ActivityContract.Presenter provideHomePresenter(ForecastService forecastService,
                                                           ForecastDatabase forecastDatabase) {
        return new ActivityPresnter(forecastService, forecastDatabase, homeView);
    }
}
