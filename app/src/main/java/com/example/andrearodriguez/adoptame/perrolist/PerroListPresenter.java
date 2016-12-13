package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.perrolist.events.PerroListEvent;
import com.example.andrearodriguez.adoptame.perrolist.ui.PerroListView;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePerro(Bebe bebe);
    void onEventMainThread (PerroListEvent event);


    void toggleFavorite (Bebe bebe);

    void showAll();
    void showFavs();

    PerroListView getView();

}
