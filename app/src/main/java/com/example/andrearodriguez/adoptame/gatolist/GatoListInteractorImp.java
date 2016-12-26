package com.example.andrearodriguez.adoptame.gatolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public class GatoListInteractorImp implements GatoListInteractor{

    private GatoListRepository repository;

    public GatoListInteractorImp(GatoListRepository repository) {
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
    public void removeGato(Bebe bebe) {
        repository.removeGato(bebe);
    }

    @Override
    public void excecute(Bebe bebe) {
        repository.updateGato(bebe);
    }

}
