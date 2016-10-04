package com.example.andrearodriguez.adoptame.fundacioneslist.di;

import com.example.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.example.andrearodriguez.adoptame.domain.di.DomainModule;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.FundationListActivity;
import com.example.andrearodriguez.adoptame.libs.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/27/16.
 */
@Singleton
@Component(modules = {FundationListModule.class, DomainModule.class, LibsModule.class, BebeAdoptaAppModule.class})
public interface FundationListComponent {
    void inject (FundationListActivity activity);
}
