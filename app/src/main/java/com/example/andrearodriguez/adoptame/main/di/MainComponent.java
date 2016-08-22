package com.example.andrearodriguez.adoptame.main.di;


import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.example.andrearodriguez.adoptame.domain.di.DomainModule;

import com.example.andrearodriguez.adoptame.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by andrearodriguez on 8/11/16.
 */
@Singleton
@Component (modules = {MainModule.class, DomainModule.class, BebeAdoptaAppModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
