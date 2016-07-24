package com.example.andrearodriguez.adoptame.libs.base.di;

import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Singleton
@Component(modules = {LibsModule.class, BebeAdoptaAppModule.class})
public interface LibsComponent {
}
