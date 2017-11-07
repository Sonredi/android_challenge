package com.foo.umbrella.data;

import com.foo.umbrella.data.api.RemoteDataSource;
import com.foo.umbrella.data.api.WeatherService;
import com.foo.umbrella.data.model.WeatherData;

import retrofit2.adapter.rxjava.Result;
import rx.Observer;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public class WeatherRepository {

    private RemoteDataSource remoteDataSource;

    public WeatherRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public void retrieveWeatherInfo(String zip, Observer<Result<WeatherData>> observer) {
        remoteDataSource.retrieveWeatherInfo(zip, observer);
    }
}
