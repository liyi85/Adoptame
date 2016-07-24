package com.example.andrearodriguez.adoptame.login.di;

import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.example.andrearodriguez.adoptame.domain.di.DomainModule;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.example.andrearodriguez.adoptame.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
