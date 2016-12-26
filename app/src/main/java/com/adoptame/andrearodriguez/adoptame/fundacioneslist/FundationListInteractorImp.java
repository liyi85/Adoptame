package com.adoptame.andrearodriguez.adoptame.fundacioneslist;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundationListInteractorImp implements FundationListInteractor{

    private FundationListRepository repository;

    public FundationListInteractorImp(FundationListRepository repository) {
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
}
