package com.adoptame.andrearodriguez.adoptame.gatolist.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListFragment;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 12/23/16.
 */

@Singleton
@Component(modules = {GatoListModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface GatoListComponent {
    void inject (GatoListFragment fragment);
}
