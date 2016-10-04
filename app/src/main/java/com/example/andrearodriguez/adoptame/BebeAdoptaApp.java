package com.example.andrearodriguez.adoptame;

import android.app.Application;

import com.example.andrearodriguez.adoptame.domain.di.DomainModule;

import com.example.andrearodriguez.adoptame.fundacioneslist.di.DaggerFundationListComponent;
import com.example.andrearodriguez.adoptame.fundacioneslist.di.FundationListComponent;
import com.example.andrearodriguez.adoptame.fundacioneslist.di.FundationListModule;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.FundationListActivity;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.FundationListView;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.adapters.OnItemClickListenerF;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;


import com.example.andrearodriguez.adoptame.main.di.DaggerMainComponent;
import com.example.andrearodriguez.adoptame.main.di.MainComponent;
import com.example.andrearodriguez.adoptame.main.di.MainModule;

import com.example.andrearodriguez.adoptame.perrolist.di.DaggerPerroLisComponent;
import com.example.andrearodriguez.adoptame.perrolist.di.PerroLisComponent;
import com.example.andrearodriguez.adoptame.perrolist.di.PerroListModule;
import com.example.andrearodriguez.adoptame.perrolist.ui.PerroListActivity;
import com.example.andrearodriguez.adoptame.perrolist.ui.PerroListView;
import com.example.andrearodriguez.adoptame.perrolist.ui.adapter.OnItemClickListener;
import com.firebase.client.Firebase;


/**
 * Created by andrearodriguez on 7/24/16.
 */
public class BebeAdoptaApp extends Application {

    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UsersPrefs";
    //private final static String FIREBASE_URL = "https://fundaciones.firebaseIO.com";
    private final static String FIREBASE_URL = "https://fundacionesapp.firebaseIO.com";
    //private final static String FIREBASE_URL = "https://adoptameapp.firebaseIO.com";


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
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }


    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }


    public MainComponent getMainCompoentn (){
        return DaggerMainComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .mainModule(new MainModule())
                .build();
    }

    public PerroLisComponent getPerroLisComponent (PerroListActivity activity, PerroListView view, OnItemClickListener clickListener){
        return DaggerPerroLisComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(activity))
                .perroListModule(new PerroListModule(view, clickListener))
                .build();
    }

    public FundationListComponent getFundationListComponent (FundationListActivity activity, FundationListView view, OnItemClickListenerF clickListenerF){
        return DaggerFundationListComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(activity))
                .fundationListModule(new FundationListModule(view, clickListenerF))
                .build();
    }

}
