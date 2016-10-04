package com.example.andrearodriguez.adoptame.fundacioneslist.di;


import com.example.andrearodriguez.adoptame.domain.FirebaseFundacionesAPI;
import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListInteractor;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListInteractorImp;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListPresenter;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListPresenterImp;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListRepository;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListRepositoryImp;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.FundationListView;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.adapters.FundationListAdapter;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.adapters.OnItemClickListenerF;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/27/16.
 */
@Module
public class FundationListModule {

    private FundationListView view;
    private OnItemClickListenerF clickListener;

    public FundationListModule(FundationListView view, OnItemClickListenerF clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    FundationListView providesFundationListView (){
        return this.view;
    }

    @Provides
    @Singleton
    FundationListPresenter providesFundationListPresenter (EventBus eventBus, FundationListView view, FundationListInteractor interactor){
        return new FundationListPresenterImp(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    FundationListInteractor providesFundationListInteractor (FundationListRepository repository){
        return new FundationListInteractorImp(repository);
    }
    @Provides
    @Singleton
    FundationListRepository providesFundationListRepository (EventBus eventBus, FirebaseFundacionesAPI firebaseAPI){
        return new FundationListRepositoryImp(eventBus, firebaseAPI);
    }

    @Provides
    @Singleton
    FundationListAdapter providesFundationListAdapter (List<Fundacion> fundacionList, OnItemClickListenerF clickListener){
        return new FundationListAdapter(fundacionList, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListenerF providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Fundacion> providesFundationList (){
        return new ArrayList<Fundacion>();
    }

}
