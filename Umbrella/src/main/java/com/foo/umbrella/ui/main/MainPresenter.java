package com.foo.umbrella.ui.main;

import android.util.Log;

import com.foo.umbrella.data.WeatherRepository;
import com.foo.umbrella.data.model.CurrentObservation;
import com.foo.umbrella.data.model.DisplayLocation;
import com.foo.umbrella.data.model.ForecastCondition;
import com.foo.umbrella.data.model.WeatherData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.adapter.rxjava.Result;
import rx.Observer;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenterTAG_";
    private WeatherRepository weatherRepository;
    private MainContract.View view;

    public MainPresenter(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void loadWeather() {
        weatherRepository.retrieveWeatherInfo("30339", new Observer<Result<WeatherData>>() {


            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(Result<WeatherData> weatherDataResult) {
//                Log.d(TAG, "onNext: " +weatherDataResult.response().body().getForecast());

                String[] currentValues = new String[4];
                CurrentObservation currentObservation = weatherDataResult.response().body().getCurrentObservation();
                currentValues[0]=currentObservation.getDisplayLocation().getFullName();
                currentValues[1]=currentObservation.getTempFahrenheit();
                currentValues[2]=currentObservation.getTempCelsius();
                currentValues[3]=currentObservation.getWeatherDescription();
                List<ForecastCondition> results = weatherDataResult.response().body().getForecast();
                Map<String, List<ForecastCondition>> mapList = new TreeMap<>();
                for (ForecastCondition result : results) {
                    String key = String.valueOf(result.getDateTime().toLocalDate());
                    if (mapList.containsKey(key)){
                        mapList.get(key).add(result);
                    } else {
                        List<ForecastCondition> tmp = new ArrayList<>();
                        tmp.add(result);
                        mapList.put(key,tmp);
                    }
                }

                view.showResults(mapList, currentValues);

            }
        });
    }
}
