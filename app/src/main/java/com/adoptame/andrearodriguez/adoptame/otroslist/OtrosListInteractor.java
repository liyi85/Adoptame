package com.adoptame.andrearodriguez.adoptame.otroslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public interface OtrosListInteractor {
    void subscribe();
    void unsubscribe();

    void removeOtro(Bebe bebe);
    void excecute(Bebe bebe);
}
