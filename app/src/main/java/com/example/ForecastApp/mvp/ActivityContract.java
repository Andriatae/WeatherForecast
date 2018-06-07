package com.example.ForecastApp.mvp;

import android.support.annotation.NonNull;

import com.example.ForecastApp.model.Day;

import java.util.List;


public interface ActivityContract {

    interface View {
        void showForecast(@NonNull List<Day> days);
        void showError(Throwable throwable);
        void showProgress(boolean shouldShow);
        void showTryAgain(boolean shouldShow);
        void setActivityTitle(String title);
    }

    interface Presenter {
        void getForecast(boolean isOnline);
        void start();
        void stop();
    }
}
