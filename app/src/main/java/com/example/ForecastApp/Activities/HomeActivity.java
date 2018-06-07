package com.example.ForecastApp.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ForecastApp.Dagger_MainActivity.ActivityModule;
import com.example.ForecastApp.Dagger_MainActivity.DaggerActivityComponent;
import com.example.ForecastApp.adapter.WeatherAdapter;
import com.example.ForecastApp.mvp.ActivityContract;
import com.example.ForecastApp.R;
import com.example.ForecastApp.DataBank.Utils;
import com.example.ForecastApp.application.App;
import com.example.ForecastApp.model.Day;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements ActivityContract.View {

    @BindView(R.id.rv_forecast)
    RecyclerView recyclerView;
    @BindView(R.id.pb_home_progress)
    ProgressBar progressBar;
    @BindView(R.id.tv_try_again)
    TextView tryAgain;

    @Inject
    ActivityContract.Presenter presenter;

    private final WeatherAdapter forecastAdapter = new WeatherAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DaggerActivityComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .activityModule(new ActivityModule(this))
                .build().inject(this);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(forecastAdapter);
        presenter.getForecast(Utils.isOnline(this));
    }

    @Override
    public void showForecast(@NonNull List<Day> days) {
        forecastAdapter.setData(days);
    }

    @Override
    public void showError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean shouldShow) {
        progressBar.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showTryAgain(boolean shouldShow) {
        if (shouldShow) {
            recyclerView.setVisibility(View.GONE);
            tryAgain.setVisibility(View.VISIBLE);
        } else {
            tryAgain.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setActivityTitle(String title) {
        setTitle(title);
    }

    @OnClick(R.id.tv_try_again)
    public void onTryAgainClicked() {
        presenter.getForecast(Utils.isOnline(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }
}
