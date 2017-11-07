package com.foo.umbrella.ui.main;

import com.foo.umbrella.di.ApplicationComponent;
import com.foo.umbrella.di.scopes.ActivityScope;

import dagger.Component;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */
@ActivityScope
@Component(modules = MainModule.class, dependencies = ApplicationComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
