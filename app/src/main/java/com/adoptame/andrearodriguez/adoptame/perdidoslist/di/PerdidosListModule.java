package com.adoptame.andrearodriguez.adoptame.perdidoslist.di;

import com.adoptame.andrearodriguez.adoptame.domain.FirebasePerdidosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.PerdidosListInteractor;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.PerdidosListInteractorImp;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.PerdidosListPresenter;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.PerdidosListPresenterImp;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.PerdidosListRepository;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.PerdidosListRepositoryImp;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.adapter.OnItemClickPerdidos;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.adapter.PerdidosListAdapter;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.ui.PerdidosListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 3/24/17.
 */

@Module
public class PerdidosListModule {

    private PerdidosListView view;
    private OnItemClickPerdidos clickListener;

    public PerdidosListModule(PerdidosListView view, OnItemClickPerdidos clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }
    @Provides
    @Singleton
    PerdidosListView providesPerdidosListView (){
        return this.view;
    }
    @Provides
    @Singleton
    PerdidosListPresenter providesPerdidosListPresenter (EventBus eventBus, PerdidosListView view, PerdidosListInteractor interactor){
        return new PerdidosListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    PerdidosListInteractor providesPerdidosListInteractor (PerdidosListRepository repository){
        return new PerdidosListInteractorImp(repository);
    }
    @Provides
    @Singleton
    PerdidosListRepository providesPerdidosListRepository (EventBus eventBus, FirebasePerdidosAPI firebasePerdidosAPI){
        return new PerdidosListRepositoryImp(eventBus, firebasePerdidosAPI);
    }
    @Provides
    @Singleton
    PerdidosListAdapter providesPerdidosListAdapter (List<Bebe> perdidosList, ImageLoader imageLoader, OnItemClickPerdidos clickListener){
        return new PerdidosListAdapter(perdidosList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickPerdidos providesOnItemClickPerdidos (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Bebe> providesPerdidosList (){
        return new ArrayList<Bebe>();
    }
}
