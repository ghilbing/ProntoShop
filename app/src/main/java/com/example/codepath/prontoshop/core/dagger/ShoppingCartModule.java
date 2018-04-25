package com.example.codepath.prontoshop.core.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.codepath.prontoshop.common.ShoppingCart;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gretel on 11/8/17.
 */

@Module
public class ShoppingCartModule {

    @Provides @Singleton
    SharedPreferences providesSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    ShoppingCart providesShoppingCart(SharedPreferences preferences){
        return new ShoppingCart(preferences);
    }

}
