package com.adoptame.andrearodriguez.adoptame.eventoslist.ui;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;

/**
 * Created by andrearodriguez on 2/19/17.
 */
public interface EventoListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addEvento(Eventos eventos);
    void removeEvento(Eventos eventos);
    void onEventoError(String error);

    void onEventoUpload();
}
