package com.adoptame.andrearodriguez.adoptame.fundacioneslist.di;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaAppModule;
import com.adoptame.andrearodriguez.adoptame.domain.di.DomainModule;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.FundationListActivity;
import com.adoptame.andrearodriguez.adoptame.libs.base.di.LibsModule;

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
