package com.example.codepath.prontoshop.core.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gretel on 11/8/17.
 */

@Module
public class BusModule {

    @Provides @Singleton
    public Bus provideBus(){
        return new Bus();
    }

}
