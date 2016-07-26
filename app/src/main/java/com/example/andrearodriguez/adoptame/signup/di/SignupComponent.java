package com.example.andrearodriguez.adoptame.signup.di;

import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.example.andrearodriguez.adoptame.domain.di.DomainModule;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.example.andrearodriguez.adoptame.login.di.LoginModule;
import com.example.andrearodriguez.adoptame.login.ui.LoginActivity;
import com.example.andrearodriguez.adoptame.signup.ui.SignupActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Singleton
@Component(modules = {SignupModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface SignupComponent {
    void inject(SignupActivity activity);
}
