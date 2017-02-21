package com.adoptame.andrearodriguez.adoptame.eventoslist.di;

import android.content.Context;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaseEventosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Eventos;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListInteractor;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListInteractorImp;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListPresenter;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListPresenterImp;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListRepository;
import com.adoptame.andrearodriguez.adoptame.eventoslist.EventosListRepositoryImp;
import com.adoptame.andrearodriguez.adoptame.eventoslist.adapter.EventosListAdapter;
import com.adoptame.andrearodriguez.adoptame.eventoslist.adapter.OnItemClickListenerEventos;
import com.adoptame.andrearodriguez.adoptame.eventoslist.ui.EventoListView;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 2/20/17.
 */

@Module
public class EventosListModule {

    private EventoListView view;
    private OnItemClickListenerEventos clickListener;

    public EventosListModule(EventoListView view, OnItemClickListenerEventos clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }
    @Provides
    @Singleton
    EventoListView providesEventoListView (){
        return this.view;
    }
    @Provides
    @Singleton
    EventosListPresenter providesEventosListPresenter (EventBus eventBus, EventoListView view, EventosListInteractor interactor){
        return new EventosListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    EventosListInteractor providesEventosListInteractor (EventosListRepository repository){
        return new EventosListInteractorImp(repository);
    }
    @Provides
    @Singleton
    EventosListRepository providesEventosListRepository (EventBus eventBus, FirebaseEventosAPI firebaseAPI){
        return new EventosListRepositoryImp(eventBus, firebaseAPI);
    }
    @Provides
    @Singleton
    EventosListAdapter providesEventosListAdapter (List<Eventos> eventosList, ImageLoader imageLoader, OnItemClickListenerEventos clickListener, Context context){
        return new EventosListAdapter(eventosList, imageLoader, clickListener, context);
    }
    @Provides
    @Singleton
    OnItemClickListenerEventos providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Eventos> providesEventosList (){
        return new ArrayList<Eventos>();
    }
}
