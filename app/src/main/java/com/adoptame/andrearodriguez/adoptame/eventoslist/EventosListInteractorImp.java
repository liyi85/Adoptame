package com.adoptame.andrearodriguez.adoptame.eventoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public class EventosListInteractorImp implements EventosListInteractor{

    private EventosListRepository repository;

    public EventosListInteractorImp(EventosListRepository repository) {
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
    public void removeEvento(Eventos eventos) {
        repository.removeEvento(eventos);
    }

    @Override
    public void excecute(Eventos eventos) {
        repository.updateEvento(eventos);
    }
}
