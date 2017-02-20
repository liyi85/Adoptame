package com.adoptame.andrearodriguez.adoptame.eventoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public interface EventosListRepository {
    void subscribe();
    void unsubscribe();

    void removeEvento(Eventos eventos);
    void updateEvento(Eventos eventos);
}
