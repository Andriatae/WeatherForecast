package com.example.ForecastApp.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ForecastApp.DataBank.Constants;
import com.example.ForecastApp.R;
import com.example.ForecastApp.DataBank.Utils;
import com.example.ForecastApp.model.Day;
import com.squareup.picasso.Picasso;

import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ForecastViewHolder> {

    private final List<Day> daysinfo;   //in Celsius


    public WeatherAdapter() {

        daysinfo = new ArrayList<>();

    }

    public void setData(@NonNull List<Day> days) {
        Objects.requireNonNull(days);
        daysinfo.clear();
        daysinfo.addAll(days);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                parent, false);
        return new ForecastViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.bind(daysinfo.get(position));
    }

    @Override
    public int getItemCount() {
        return daysinfo.size();
    }



    public void changeToF() {
        List<Day>daysWithF=new ArrayList<Day>(daysinfo);


        for (Day d: daysWithF){

            d.setCorf(true);

        }
        setData(daysWithF);

    }

    public void changeToC() {

        List<Day>daysWithC=new ArrayList<Day>(daysinfo);


        for (Day d: daysWithC){

            d.setCorf(true);

        }
        setData(daysWithC);


    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dayandtime)
        TextView dayAndTime;
        @BindView(R.id.tv_condition)
        TextView condition;
        @BindView(R.id.tv_clouds)
        TextView clouds;
        @BindView(R.id.tv_temparature)
        TextView temparature;
        @BindView(R.id.tv_wind)
        TextView wind;
        @BindView(R.id.iv_weather_icon)
        ImageView weatherIcon;

        private final Resources resources;

        ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            resources = itemView.getContext().getResources();
        }

        void bind(Day day) {
            Picasso.get().load(Constants.IMAGE_BASE_URL+day.weather.get(0).icon+Constants.ICON_END)
                    .into(weatherIcon);
            dayAndTime.setText(Utils.getDateFormatted(day.dateAndTime));
            condition.setText(day.weather.get(0).description.toUpperCase());
            clouds.setText(resources.getString(R.string.cloud_percentage, day.clouds.all));

            if(day.getCorf()==true) {

                temparature.setText(Utils.getCelsiusFromKelvin(day.main.temp));

            }
            else{

                double fahren = (9.0/5.0)*Utils.getCelsiusDouble(day.main.temp) + 32;

                temparature.setText(Double.toString(fahren));
            }
            wind.setText(resources.getString(R.string.wind_speed,
                    Utils.DoubleToDecimal(day.wind.speed)));
        }


    }
}
