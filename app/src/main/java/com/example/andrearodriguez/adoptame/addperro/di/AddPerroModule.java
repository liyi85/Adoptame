package com.example.andrearodriguez.adoptame.addperro.di;

import com.example.andrearodriguez.adoptame.addperro.AddPerroInteractor;
import com.example.andrearodriguez.adoptame.addperro.AddPerroInteractorImp;
import com.example.andrearodriguez.adoptame.addperro.AddPerroPresenter;
import com.example.andrearodriguez.adoptame.addperro.AddPerroPresenterImp;
import com.example.andrearodriguez.adoptame.addperro.AddPerroRepository;
import com.example.andrearodriguez.adoptame.addperro.AddPerroRepositoryImp;
import com.example.andrearodriguez.adoptame.addperro.ui.AddPerroView;
import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Module
public class AddPerroModule {
    private AddPerroView view;

    public AddPerroModule(AddPerroView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    AddPerroView providesAddPerroView(){
        return this.view;
    }
    @Provides
    @Singleton
    AddPerroPresenter providesAddPerroPresenter (AddPerroView view, EventBus eventBus, AddPerroInteractor interactor){
        return new AddPerroPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    AddPerroInteractor providesAddPerroInteractor (AddPerroRepository repository){
        return new AddPerroInteractorImp(repository);
    }

    @Provides
    @Singleton
    AddPerroRepository providesAddPerroRepository (EventBus eventBus, FirebaseAPI firebaseAPI, ImageStorage imageStorage){
        return new AddPerroRepositoryImp(eventBus, firebaseAPI, imageStorage);
    }
}
