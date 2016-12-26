package com.adoptame.andrearodriguez.adoptame.libs.base.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Singleton
@Component(modules = {LibsModule.class, BebeAdoptaAppModule.class})
public interface LibsComponent {
}
