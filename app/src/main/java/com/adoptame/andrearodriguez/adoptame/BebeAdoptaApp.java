package com.adoptame.andrearodriguez.adoptame;

import android.app.Application;

import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.eventoslist.adapter.OnItemClickListenerEventos;
import com.adoptame.andrearodriguez.adoptame.eventoslist.di.DaggerEventosListComponent;
import com.adoptame.andrearodriguez.adoptame.eventoslist.di.EventosListComponent;
import com.adoptame.andrearodriguez.adoptame.eventoslist.di.EventosListModule;
import com.adoptame.andrearodriguez.adoptame.eventoslist.ui.EventoListView;
import com.adoptame.andrearodriguez.adoptame.eventoslist.ui.EventosListFragment;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.di.DaggerFundationListComponent;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.di.FundationListComponent;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.di.FundationListModule;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.FundationListActivity;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.FundationListView;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.adapters.OnItemClickListenerF;
import com.adoptame.andrearodriguez.adoptame.gatolist.di.DaggerGatoListComponent;
import com.adoptame.andrearodriguez.adoptame.gatolist.di.GatoListComponent;
import com.adoptame.andrearodriguez.adoptame.gatolist.di.GatoListModule;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListFragment;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListView;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.adoptame.andrearodriguez.adoptame.main.di.DaggerMainComponent;
import com.adoptame.andrearodriguez.adoptame.main.di.MainComponent;
import com.adoptame.andrearodriguez.adoptame.main.di.MainModule;
import com.adoptame.andrearodriguez.adoptame.otroslist.di.DaggerOtroListComponent;
import com.adoptame.andrearodriguez.adoptame.otroslist.di.OtroListComponent;
import com.adoptame.andrearodriguez.adoptame.otroslist.di.OtroListModule;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.OtroListView;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.OtrosListFragment;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter.OnItemClickListenerOtros;
import com.adoptame.andrearodriguez.adoptame.perrolist.di.DaggerPerroLisComponent;
import com.adoptame.andrearodriguez.adoptame.perrolist.di.PerroLisComponent;
import com.adoptame.andrearodriguez.adoptame.perrolist.di.PerroListModule;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.PerroListFragment;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.PerroListView;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.adapter.OnItemClickListener;
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


    @Override
    public void onTerminate() {
        super.onTerminate();

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

    public PerroLisComponent getPerroLisComponent (PerroListFragment fragment, PerroListView view, OnItemClickListener clickListener){
        return DaggerPerroLisComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .perroListModule(new PerroListModule(view, clickListener))
                .build();
    }

    public FundationListComponent getFundationListComponent (FundationListActivity activity, FundationListView view, OnItemClickListenerF clickListenerF){
        return DaggerFundationListComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .fundationListModule(new FundationListModule(view, clickListenerF))
                .build();
    }

    public GatoListComponent getGatoListComponent (GatoListFragment fragment, GatoListView view, com.adoptame.andrearodriguez.adoptame.gatolist.ui.adapter.OnItemClickListener clickListenerG){
        return DaggerGatoListComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .gatoListModule(new GatoListModule(view, clickListenerG))
                .build();
    }

    public OtroListComponent getOtroListComponent (OtrosListFragment fragment, OtroListView view, OnItemClickListenerOtros onItemClickListener){
        return DaggerOtroListComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .otroListModule(new OtroListModule(view, onItemClickListener))
                .build();
    }

    public EventosListComponent getEventosListComponent (EventosListFragment fragment, EventoListView view, OnItemClickListenerEventos clickListenerE){
        return DaggerEventosListComponent
                .builder()
                .bebeAdoptaAppModule(bebeAdoptaAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .eventosListModule(new EventosListModule(view, clickListenerE))
                .build();
    }

}
