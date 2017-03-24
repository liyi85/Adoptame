package com.adoptame.andrearodriguez.adoptame.perdidoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface PerdidosListInteractor {
    void subscribe();
    void unsubscribe();

    void removePerdido(Bebe bebe);
}
