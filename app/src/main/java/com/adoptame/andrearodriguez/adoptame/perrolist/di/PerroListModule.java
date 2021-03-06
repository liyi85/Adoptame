package com.adoptame.andrearodriguez.adoptame.perrolist.di;

import android.content.Context;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;
import com.adoptame.andrearodriguez.adoptame.perrolist.PerroListInteractor;
import com.adoptame.andrearodriguez.adoptame.perrolist.PerroListInteractorImp;
import com.adoptame.andrearodriguez.adoptame.perrolist.PerroListPresenter;
import com.adoptame.andrearodriguez.adoptame.perrolist.PerroListPresenterImp;
import com.adoptame.andrearodriguez.adoptame.perrolist.PerroListRepository;
import com.adoptame.andrearodriguez.adoptame.perrolist.PerroListRepositoryImp;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.PerroListView;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.adapter.OnItemClickListener;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.adapter.PerroListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Module
public class PerroListModule {

    private PerroListView view;
    private OnItemClickListener clickListener;


    public PerroListModule(PerroListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;

    }

    @Provides
    @Singleton
    PerroListView providesPerroListView (){
        return this.view;
    }
    @Provides
    @Singleton
    PerroListPresenter providesPerroListPresenter (EventBus eventBus, PerroListView view, PerroListInteractor interactor){
        return new PerroListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    PerroListInteractor providesPerroListInteractor (PerroListRepository repository){
        return new PerroListInteractorImp(repository);
    }
    @Provides
    @Singleton
    PerroListRepository providesPerroListRepository (EventBus eventBus, FirebaseAPI firebaseAPI){
        return new PerroListRepositoryImp(eventBus, firebaseAPI);
    }
    @Provides
    @Singleton
    PerroListAdapter providesPerroListAdapter (List<Bebe> perroList, ImageLoader imageLoader, OnItemClickListener clickListener, Context context){
        return new PerroListAdapter(perroList, imageLoader, clickListener, context);
    }
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Bebe> providesPerroList (){
        return new ArrayList<Bebe>();
    }
}
