package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListInteractorImp {
    void subscribe();
    void unsubscribe();

    void removePerro(Bebe bebe);
}
