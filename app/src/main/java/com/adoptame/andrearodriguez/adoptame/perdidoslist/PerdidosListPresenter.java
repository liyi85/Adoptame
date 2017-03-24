package com.adoptame.andrearodriguez.adoptame.perdidoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.event.PerdidosListEvent;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface PerdidosListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePerdido(Bebe bebe);
    void onEventMainThread (PerdidosListEvent event);
}
