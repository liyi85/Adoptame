package com.example.andrearodriguez.adoptame;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by andrearodriguez on 7/23/16.
 */
public class AdoptameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
