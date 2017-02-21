package com.adoptame.andrearodriguez.adoptame.eventoslist.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.eventoslist.ui.EventosListFragment;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 2/20/17.
 */

@Singleton
@Component (modules = {EventosListModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface EventosListComponent {
    void inject (EventosListFragment fragment);
}
