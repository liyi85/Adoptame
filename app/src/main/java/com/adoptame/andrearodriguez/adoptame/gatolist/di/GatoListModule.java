package com.adoptame.andrearodriguez.adoptame.gatolist.di;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaseGatosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListInteractor;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListInteractorImp;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListPresenter;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListPresenterImp;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListRepository;
import com.adoptame.andrearodriguez.adoptame.gatolist.GatoListRepositoryImp;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListView;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.adapter.GatoListAdapter;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.adapter.OnItemClickListener;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;

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
    GatoListAdapter providesGatoListAdapter (List<Bebe> gatoList, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new GatoListAdapter(gatoList, imageLoader, clickListener);
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