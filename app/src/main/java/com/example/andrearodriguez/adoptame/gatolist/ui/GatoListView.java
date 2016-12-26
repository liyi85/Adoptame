package com.example.andrearodriguez.adoptame.gatolist.ui;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public interface GatoListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addGato(Bebe bebe);
    void removeGato(Bebe bebe);
    void onGatoError(String error);

    void onGatoUpload();
}
