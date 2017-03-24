package com.adoptame.andrearodriguez.adoptame.perdidoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public class PerdidosListInteractorImp implements PerdidosListInteractor{

    PerdidosListRepository repository;

    public PerdidosListInteractorImp(PerdidosListRepository repository) {
        this.repository = repository;
    }
    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void removePerdido(Bebe bebe) {
        repository.removePerdido(bebe);
    }

}
