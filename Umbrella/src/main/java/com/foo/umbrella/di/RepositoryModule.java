package com.foo.umbrella.di;

import com.foo.umbrella.data.WeatherRepository;
import com.foo.umbrella.data.api.RemoteDataSource;
import com.foo.umbrella.data.api.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    WeatherRepository provideWeatherRepository(RemoteDataSource remoteDataSource) {
        return new WeatherRepository(remoteDataSource);
    }

    @Singleton
    @Provides
    RemoteDataSource provideRemoteDataSource(WeatherService weatherService) {
        return new RemoteDataSource(weatherService);
    }
}
