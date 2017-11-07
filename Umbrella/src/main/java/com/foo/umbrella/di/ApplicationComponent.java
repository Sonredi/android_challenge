package com.foo.umbrella.di;

import com.foo.umbrella.data.WeatherRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */
@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    WeatherRepository weatherRepository();
}
