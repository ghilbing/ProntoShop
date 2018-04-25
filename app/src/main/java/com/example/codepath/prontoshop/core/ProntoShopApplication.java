package com.example.codepath.prontoshop.core;

import android.app.Application;

import com.example.codepath.prontoshop.core.dagger.AppComponent;
import com.example.codepath.prontoshop.core.dagger.AppModule;
import com.example.codepath.prontoshop.core.dagger.DaggerAppComponent;
import com.squareup.otto.Bus;

/**
 * Created by gretel on 11/8/17.
 */

public class ProntoShopApplication extends Application{
    
    private static AppComponent appComponent;
    private static ProntoShopApplication instance = new ProntoShopApplication();

    private Bus bus;
    public Bus getBus(){
        return bus;
    }

    public static ProntoShopApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    public AppComponent getAppComponent() {

        if (appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }
}
