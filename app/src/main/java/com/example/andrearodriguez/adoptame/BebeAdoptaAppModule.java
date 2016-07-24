package com.example.andrearodriguez.adoptame;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 7/24/16.
 */

@Module
public class BebeAdoptaAppModule {
    BebeAdoptaApp app;

    public BebeAdoptaAppModule(BebeAdoptaApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application aplication){
        return aplication.getSharedPreferences(app.getSharedPreferencesName(), Context.MODE_PRIVATE);
    }
    @Provides
    @Singleton
    Application providesApplication(){
        return this.app;
    }
}
