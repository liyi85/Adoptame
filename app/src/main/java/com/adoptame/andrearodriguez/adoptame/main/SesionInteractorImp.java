package com.adoptame.andrearodriguez.adoptame.main;

/**
 * Created by andrearodriguez on 8/11/16.
 */
public class SesionInteractorImp implements SesionInteractor{

    MainRepository repository;

    public SesionInteractorImp(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
