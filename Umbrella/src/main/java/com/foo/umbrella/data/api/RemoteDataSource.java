package com.foo.umbrella.data.api;

import com.foo.umbrella.data.model.WeatherData;

import retrofit2.adapter.rxjava.Result;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public class RemoteDataSource {
    private WeatherService weatherService;

    public RemoteDataSource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void retrieveWeatherInfo(String zip, Observer<Result<WeatherData>> observer) {
        weatherService.forecastForZipObservable(zip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
