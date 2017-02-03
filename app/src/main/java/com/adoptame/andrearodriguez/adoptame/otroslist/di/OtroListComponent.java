package com.adoptame.andrearodriguez.adoptame.otroslist.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.OtrosListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 2/2/17.
 */
@Singleton
@Component(modules = {OtroListModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface OtroListComponent {
    void inject (OtrosListFragment fragment);
}
