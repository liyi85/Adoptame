package com.example.andrearodriguez.adoptame.gatolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public interface GatoListInteractor {
    void subscribe();
    void unsubscribe();

    void removeGato(Bebe bebe);

    void excecute(Bebe bebe);
}
