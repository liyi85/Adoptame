package com.example.andrearodriguez.adoptame.gatolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.gatolist.event.GatoListEvent;
import com.example.andrearodriguez.adoptame.gatolist.ui.GatoListView;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public interface GatoListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removeGato(Bebe bebe);
    void onEventMainThread (GatoListEvent event);


    GatoListView getView();
}
