package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;

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

    @Override
    public void searchFav() {
        repository.getFavoritesBebe();
    }

    @Override
    public void searchAll() {
        repository.getAll();
    }


}
