package com.foo.umbrella.base;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();
}
