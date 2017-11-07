package com.foo.umbrella.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.foo.umbrella.R;
import com.foo.umbrella.UmbrellaApp;
import com.foo.umbrella.data.model.DisplayLocation;
import com.foo.umbrella.data.model.ForecastCondition;
import com.foo.umbrella.di.ApplicationComponent;
import com.foo.umbrella.ui.preferences.PreferencesActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivityTAG_";
    @Inject
    MainPresenter mainPresenter;

    private RecyclerView recyclerView;
    private MinimalAdapter minimalAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Map<String, List<ForecastCondition>> resultList;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = this.getSupportActionBar();

        recyclerView = (RecyclerView) findViewById(R.id.a_main_recycler);

        injectDependencies();
        mainPresenter.attachView(this);


        resultList = new HashMap<>();
        minimalAdapter = new MinimalAdapter(resultList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(minimalAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.loadWeather();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void injectDependencies() {
        ApplicationComponent applicationComponent =
                ((UmbrellaApp) getApplication()).getApplicationComponent();

        DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .mainModule(new MainModule())
                .build()
                .inject(this);
    }

    @Override
    public void showResults(Map<String, List<ForecastCondition>> results, String[] currentValues) {
       actionBar.setTitle(currentValues[0]);
        for (String key : results.keySet()) {
            Log.d(TAG, "showResults: " + key);
        }

        resultList.clear();
        resultList.putAll(results);
        minimalAdapter.updateKeySet();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    //Menu section

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.m_main_pref) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
