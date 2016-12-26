package com.example.andrearodriguez.adoptame.gatolist.di;

import com.example.andrearodriguez.adoptame.domain.FirebaseGatosAPI;
import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.gatolist.GatoListInteractor;
import com.example.andrearodriguez.adoptame.gatolist.GatoListInteractorImp;
import com.example.andrearodriguez.adoptame.gatolist.GatoListPresenter;
import com.example.andrearodriguez.adoptame.gatolist.GatoListPresenterImp;
import com.example.andrearodriguez.adoptame.gatolist.GatoListRepository;
import com.example.andrearodriguez.adoptame.gatolist.GatoListRepositoryImp;
import com.example.andrearodriguez.adoptame.gatolist.ui.GatoListView;
import com.example.andrearodriguez.adoptame.gatolist.ui.adapter.GatoListAdapter;
import com.example.andrearodriguez.adoptame.gatolist.ui.adapter.OnItemClickListener;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.libs.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 12/23/16.
 */

@Module
public class GatoListModule {
    private GatoListView view;
    private OnItemClickListener clickListener;


    public GatoListModule(GatoListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }



    @Provides
    @Singleton
    GatoListView providesGatoListView (){
        return this.view;
    }

    @Provides
    @Singleton
    GatoListPresenter providesGatoListPresenter (EventBus eventBus, GatoListView view, GatoListInteractor interactor){
        return new GatoListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    GatoListInteractor providesGatoListInteractor (GatoListRepository repository){
        return new GatoListInteractorImp(repository);
    }
    @Provides
    @Singleton
    GatoListRepository providesGatoListRepository (EventBus eventBus, FirebaseGatosAPI firebaseAPI){
        return new GatoListRepositoryImp(eventBus, firebaseAPI);
    }
    @Provides
    @Singleton
    GatoListAdapter providesGatoListAdapter (List<Bebe> perroList, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new GatoListAdapter(perroList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Bebe> providesGatoList (){
        return new ArrayList<Bebe>();
    }
}