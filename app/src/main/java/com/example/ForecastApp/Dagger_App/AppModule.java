package com.example.ForecastApp.Dagger_App;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.ForecastApp.Database.ForecastDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    public ForecastDatabase provideForecastDatabase(Context context) {
        return Room.databaseBuilder(context, ForecastDatabase.class, "forecast").build();
    }

}
