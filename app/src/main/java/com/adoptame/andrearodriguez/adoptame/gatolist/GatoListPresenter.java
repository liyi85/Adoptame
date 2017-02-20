package com.adoptame.andrearodriguez.adoptame.gatolist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.gatolist.event.GatoListEvent;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListView;

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
