package com.adoptame.andrearodriguez.adoptame.perrolist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public class PerroListInteractorImp implements PerroListInteractor{

    private PerroListRepository repository;

    public PerroListInteractorImp(PerroListRepository repository) {
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
    public void removePerro(Bebe bebe) {
        repository.removePerro(bebe);
    }

    @Override
    public void excecute(Bebe bebe) {
        repository.updatePerro(bebe);

    }

}
