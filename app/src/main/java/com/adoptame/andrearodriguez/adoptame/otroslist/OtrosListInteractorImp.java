package com.adoptame.andrearodriguez.adoptame.otroslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public class OtrosListInteractorImp implements OtrosListInteractor{

    private OtrosListRepository repository;

    public OtrosListInteractorImp(OtrosListRepository repository) {
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
    public void removeOtro(Bebe bebe) {
        repository.removeOtro(bebe);
    }

    @Override
    public void excecute(Bebe bebe) {
        repository.updateOtro(bebe);

    }

}
