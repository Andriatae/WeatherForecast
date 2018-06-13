package com.example.ForecastApp.DataBank;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utils {
    private static final double KELVIN_CONSTANT =   273.15;

    public static String DoubleToDecimal(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        return decimalFormat.format(value);
    }



    public static String getDateFormatted(long value) {
        DateTime dateTime = new DateTime(value*1000L, DateTimeZone.getDefault()); //Converting to milliseconds
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("d MMM E h:m a");
        return dateTimeFormatter.print(dateTime);
    }

    public static String getCelsiusFromKelvin(double value) {
        double tempInCelsius = value - KELVIN_CONSTANT;
        return String.valueOf(DoubleToDecimal(tempInCelsius)) + Constants.CELSIUS;
    }


    public static double getCelsiusDouble(double value){
        double tempInCelsius = value - KELVIN_CONSTANT;

        return tempInCelsius;
    }
    public static boolean isItLive(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }


    public static double getFahrenheitFromCelsius(double temp) {

        double fahrenheit = (9.0/5.0)*temp + 32;

        return  fahrenheit;

    }


}
