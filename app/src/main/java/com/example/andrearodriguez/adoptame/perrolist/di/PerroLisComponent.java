package com.example.andrearodriguez.adoptame.perrolist.di;

import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.example.andrearodriguez.adoptame.domain.di.DomainModule;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;
import com.example.andrearodriguez.adoptame.perrolist.ui.PerroListFragment;

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
