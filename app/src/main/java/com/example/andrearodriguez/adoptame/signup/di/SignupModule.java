package com.example.andrearodriguez.adoptame.signup.di;

import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.login.LoginInteractor;
import com.example.andrearodriguez.adoptame.login.LoginInteractorImp;
import com.example.andrearodriguez.adoptame.login.LoginPresenter;
import com.example.andrearodriguez.adoptame.login.LoginPresenterImp;
import com.example.andrearodriguez.adoptame.login.LoginRepository;
import com.example.andrearodriguez.adoptame.login.LoginRepositoryImp;
import com.example.andrearodriguez.adoptame.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Module
public class SignupModule {
    LoginView view;

    public SignupModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView (){
        return this.view;
    }
    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter (EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor){
        return new LoginPresenterImp(eventBus, loginView, loginInteractor);
    }
    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor (LoginRepository repository){
        return new LoginInteractorImp(repository);
    }
    @Provides
    @Singleton
    LoginRepository providesLoginRepository (EventBus eventBus, FirebaseAPI firebaseAPI){
        return new LoginRepositoryImp(eventBus, firebaseAPI);
    }
}
