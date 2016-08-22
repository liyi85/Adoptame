package com.example.andrearodriguez.adoptame.addperro.di;

import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.example.andrearodriguez.adoptame.addperro.ui.AddPerroFragment;
import com.example.andrearodriguez.adoptame.domain.di.DomainModule;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Singleton
@Component (modules = {AddPerroModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface AddPerroComponent {
    void inject (AddPerroFragment fragment);
}
