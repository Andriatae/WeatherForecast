package com.example.ForecastApp.mvp;

import com.example.ForecastApp.DataBank.Constants;
import com.example.ForecastApp.Database.ForecastDatabase;
import com.example.ForecastApp.model.Day;
import com.example.ForecastApp.model.Forecast;
import com.example.ForecastApp.Network.ForecastService;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityPresnter implements ActivityContract.Presenter{


    private final ForecastService forecastService;
    private final CompositeDisposable compositeDisposable;
    private final ActivityContract.View view;
    private final ForecastDatabase forecastDatabase;




    public ActivityPresnter(ForecastService forecastService, ForecastDatabase forecastDatabase,
                            ActivityContract.View view) {
        this.forecastService = forecastService;
        this.forecastDatabase = forecastDatabase;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }




    private Observable<Forecast> ForecastFromDb() {
        return forecastDatabase.forecastDao()
                .getForecast()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(this::handleNoData) //Maybe completes without emitting anything when the table is empty
                .toObservable();
    }

    private Observable<Forecast> getForecastFromAPI() {
        return forecastService.FiveDayForecast(Constants.ID_FOR_CITY, Constants.API_KEY)
                .doOnNext(this::addToDb);
    }


    @Override
    public void Forecast_Retrieval(boolean isOnline) {
        Observable<Forecast> forecastObservable = isOnline ? getForecastFromAPI() : ForecastFromDb();
        compositeDisposable.add(forecastObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(forecast -> view.setActivityTitle(forecast.city.name))
                .map(forecast -> forecast.days)
                .doOnSubscribe(disposable -> view.showProgress(true))
                .doOnTerminate(() -> view.showProgress(false))
                .subscribe(this::handleResult, view::showError));
    }

    @Override
    public void start() { }

    @Override
    public void stop() {
        compositeDisposable.clear();
    }

    private void handleNoData() {
        handleResult(Collections.emptyList());
    }

    private void handleResult(List<Day> days) {
        if (days.isEmpty()) {
            view.showTryAgain(true);
        } else {
            view.showTryAgain(false);
            view.showForecast(days);
        }
    }

    private void addToDb(Forecast forecast) {
        forecastDatabase.forecastDao()
                .insertForecasts(forecast);
    }
}
