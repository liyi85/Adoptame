package com.adoptame.andrearodriguez.adoptame.eventoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;
import com.adoptame.andrearodriguez.adoptame.eventoslist.events.EventosListEvent;
import com.adoptame.andrearodriguez.adoptame.eventoslist.ui.EventoListView;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public interface EventosListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removeEvento(Eventos eventos);
    void onEventMainThread (EventosListEvent event);

    EventoListView getView();
}
