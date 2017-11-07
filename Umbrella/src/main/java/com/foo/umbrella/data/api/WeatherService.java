package com.foo.umbrella.data.api;

import com.foo.umbrella.BuildConfig;
import com.foo.umbrella.data.model.WeatherData;
import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Retrofit interface for fetching weather data
 */
public interface WeatherService {

    /**
     * aaa5b925c24853b4
     * Get the forecast for a given zip code using {@link Call}
     */
    @GET("/api/aaa5b925c24853b4/conditions/hourly/q/{zip}.json")
    Call<WeatherData> forecastForZipCallable(@Path("zip") String zipCode);

    /**
     * Get the forecast for a given zip code using {@link Observable}
     */
    @GET("/api/aaa5b925c24853b4/conditions/hourly/q/{zip}.json")
    Observable<Result<WeatherData>> forecastForZipObservable(@Path("zip") String zipCode);
}
