package com.adoptame.andrearodriguez.adoptame.perrolist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.perrolist.events.PerroListEvent;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.PerroListView;

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

    PerroListView getView();

}
