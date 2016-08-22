package com.example.andrearodriguez.adoptame.main.di;

import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.main.MainPresenter;
import com.example.andrearodriguez.adoptame.main.MainPresenterImp;
import com.example.andrearodriguez.adoptame.main.MainRepository;
import com.example.andrearodriguez.adoptame.main.MainRepositoryImp;
import com.example.andrearodriguez.adoptame.main.SesionInteractor;
import com.example.andrearodriguez.adoptame.main.SesionInteractorImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 8/11/16.
 */
@Module
public class MainModule {

    @Provides
    @Singleton
    MainPresenter providesMainPresenter (SesionInteractor sesionInteractor){
        return new MainPresenterImp(sesionInteractor);
    }

    @Provides
    @Singleton
    SesionInteractor providesSesionInteractor (MainRepository repository){
        return new SesionInteractorImp(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository (FirebaseAPI firebaseAPI){
        return new MainRepositoryImp(firebaseAPI);
    }
}
