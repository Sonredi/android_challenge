package com.foo.umbrella;

import android.app.Application;

import com.foo.umbrella.di.ApplicationComponent;
import com.foo.umbrella.di.ContextModule;
import com.foo.umbrella.di.DaggerApplicationComponent;
import com.foo.umbrella.di.NetworkModule;
import com.foo.umbrella.di.RepositoryModule;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class UmbrellaApp extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    applicationComponent = DaggerApplicationComponent.builder()
            .contextModule(new ContextModule(this))
            .networkModule(new NetworkModule())
            .repositoryModule(new RepositoryModule())
            .build();

  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
