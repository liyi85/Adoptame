package com.adoptame.andrearodriguez.adoptame.perdidoslist.ui;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface PerdidosListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPerdido(Bebe bebe);
    void removePerdido(Bebe bebe);
    void onPerdidoError(String error);
}
