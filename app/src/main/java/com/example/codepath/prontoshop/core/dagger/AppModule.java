package com.example.codepath.prontoshop.core.dagger;

import android.content.Context;

import com.example.codepath.prontoshop.core.ProntoShopApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gretel on 11/7/17.
 */
@Module
public class AppModule {

    private final ProntoShopApplication app;


    public AppModule(ProntoShopApplication app) {
        this.app = app;
    }

    @Provides @Singleton
    public ProntoShopApplication provideApp(){
        return app;
    }

    @Provides @Singleton
    public Context provideContext(){
        return app;
    }
}
