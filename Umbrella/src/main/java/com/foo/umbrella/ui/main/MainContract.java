package com.foo.umbrella.ui.main;

import com.foo.umbrella.base.BasePresenter;
import com.foo.umbrella.base.BaseView;
import com.foo.umbrella.data.model.DisplayLocation;
import com.foo.umbrella.data.model.ForecastCondition;

import java.util.List;
import java.util.Map;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public interface MainContract {

    interface View extends BaseView {
        void showResults(Map<String, List<ForecastCondition>> results, String[] currectValues);

        void showProgress();

        void hideProgress();
    }

    interface Presenter extends BasePresenter<View> {
        void loadWeather();
    }


}
