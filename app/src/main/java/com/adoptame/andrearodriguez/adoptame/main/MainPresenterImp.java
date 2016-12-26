package com.adoptame.andrearodriguez.adoptame.main;

/**
 * Created by andrearodriguez on 8/11/16.
 */
public class MainPresenterImp implements MainPresenter{

    private SesionInteractor sesionInteractor;

    public MainPresenterImp(SesionInteractor sesionInteractor) {
        this.sesionInteractor = sesionInteractor;
    }

    @Override
    public void logout() {
        sesionInteractor.logout();
    }
}
