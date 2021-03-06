package com.adoptame.andrearodriguez.adoptame.perrolist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListRepository {
    void subscribe();
    void unsubscribe();

    void removePerro(Bebe bebe);
    void updatePerro(Bebe bebe);

}
