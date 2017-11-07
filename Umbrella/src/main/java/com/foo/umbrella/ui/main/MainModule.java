package com.foo.umbrella.ui.main;

import com.foo.umbrella.data.WeatherRepository;
import com.foo.umbrella.di.scopes.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */
@Module
public class MainModule {

    @ActivityScope
    @Provides
    MainPresenter provideMainPresenter(WeatherRepository weatherRepository) {
        return new MainPresenter(weatherRepository);
    }
}
