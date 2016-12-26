package com.adoptame.andrearodriguez.adoptame.perrolist.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.PerroListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 8/22/16.
 */


@Singleton
@Component (modules = {PerroListModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface PerroLisComponent {
    void inject (PerroListFragment fragment);
}
