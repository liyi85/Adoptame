package com.example.andrearodriguez.adoptame;

import android.app.Application;


import com.example.andrearodriguez.adoptame.domain.di.DomainModule;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.example.andrearodriguez.adoptame.login.di.DaggerLoginComponent;
import com.example.andrearodriguez.adoptame.login.di.LoginComponent;
import com.example.andrearodriguez.adoptame.login.di.LoginModule;
import com.example.andrearodriguez.adoptame.login.ui.LoginView;
import com.example.andrearodriguez.adoptame.signup.di.DaggerSignupComponent;
import com.example.andrearodriguez.adoptame.signup.di.SignupComponent;
import com.example.andrearodriguez.adoptame.signup.di.SignupModule;
import com.firebase.client.Firebase;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public class BebeAdoptaApp extends Application {

    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UsersPrefs";
    private final static String FIREBASE_URL = "https://adoptameapp.firebaseIO.com";

    private DomainModule domainModule;
    private BebeAdoptaAppModule bebeAdoptaAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        bebeAdoptaAppModule = new BebeAdoptaAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }
    public SignupComponent getSignupComponent(LoginView view){
        return DaggerSignupComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .signupModule(new SignupModule(view))
                .build();
    }

}
