package com.foo.umbrella.di;

import android.content.Context;

import com.foo.umbrella.data.AutoValueMoshi_MoshiAdapterFactory;
import com.foo.umbrella.data.ForecastConditionAdapter;
import com.foo.umbrella.data.api.WeatherService;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

import static com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES;

/**
 * Created by Aptivist-U002 on 11/1/2017.
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, Moshi moshi) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl("http://api.wunderground.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Context context) {
        final int DISK_CACHE_SIZE = (int) MEGABYTES.toBytes(50);
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(context.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }

    @Singleton
    @Provides
    Moshi provideMoshi(JsonAdapter.Factory factory) {
        return new Moshi.Builder()
                .add(new ForecastConditionAdapter())
                .add(factory)
                .build();
    }

    @Singleton
    @Provides
    WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }

    @Singleton
    @Provides
    JsonAdapter.Factory provideMoshiFactory() {
        return new AutoValueMoshi_MoshiAdapterFactory();
    }

    @Singleton
    @Provides
    Picasso providePicasso(Context context, OkHttpClient okHttpClient) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(okHttpClient))
                .listener((picasso, uri, e) -> Timber.e(e, "Failed to load image: %s", uri))
                .build();
    }
}
