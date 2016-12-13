package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListRepository {
    void subscribe();
    void unsubscribe();

    void removePerro(Bebe bebe);
    void updatePerro(Bebe bebe);

    void getFavoritesBebe();
    void getAll();
}
