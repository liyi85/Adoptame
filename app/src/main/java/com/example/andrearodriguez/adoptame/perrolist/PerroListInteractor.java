package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListInteractor {
    void subscribe();
    void unsubscribe();

    void removePerro(Bebe bebe);

    void excecute(Bebe bebe);
    void searchFav();
    void searchAll();



}
