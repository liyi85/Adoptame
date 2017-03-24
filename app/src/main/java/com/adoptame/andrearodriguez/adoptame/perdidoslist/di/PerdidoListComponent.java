package com.adoptame.andrearodriguez.adoptame.perdidoslist.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.ui.PerdidosListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 3/24/17.
 */

@Singleton
@Component(modules = {PerdidosListModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface PerdidoListComponent {
    void inject(PerdidosListFragment fragment);
}
