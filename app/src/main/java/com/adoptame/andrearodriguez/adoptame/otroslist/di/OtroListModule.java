package com.adoptame.andrearodriguez.adoptame.otroslist.di;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaseOtrosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.libs.base.ImageLoader;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListInteractor;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListInteractorImp;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListPresenter;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListPresenterImp;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListRepository;
import com.adoptame.andrearodriguez.adoptame.otroslist.OtrosListRepositoryImp;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.OtroListView;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter.OnItemClickListenerOtros;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter.OtroListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 2/2/17.
 */

@Module
public class OtroListModule {

    private OtroListView view;
    private OnItemClickListenerOtros clickListener;

    public OtroListModule(OtroListView view, OnItemClickListenerOtros clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    OtroListView providesOtroListView (){
        return this.view;
    }

    @Provides
    @Singleton
    OtrosListPresenter providesOtrosListPresenter (EventBus eventBus, OtroListView view, OtrosListInteractor interactor){
        return new OtrosListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    OtrosListInteractor providesOtrosListInteractor (OtrosListRepository repository){
        return new OtrosListInteractorImp(repository);
    }
    @Provides
    @Singleton
    OtrosListRepository providesOtrosListRepository (EventBus eventBus, FirebaseOtrosAPI firebaseAPI){
        return new OtrosListRepositoryImp(eventBus, firebaseAPI);
    }
    @Provides
    @Singleton
    OtroListAdapter providesOtroListAdapter (List<Bebe> otroList, ImageLoader imageLoader, OnItemClickListenerOtros clickListener){
        return new OtroListAdapter(otroList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListenerOtros providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Bebe> providesOtroList (){
        return new ArrayList<Bebe>();
    }

}
