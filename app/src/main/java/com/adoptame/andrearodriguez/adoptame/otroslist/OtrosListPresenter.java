package com.adoptame.andrearodriguez.adoptame.otroslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.otroslist.event.OtrosListEvent;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.OtroListView;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public interface OtrosListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removeOtro(Bebe bebe);
    void onEventMainThread (OtrosListEvent event);

    OtroListView getView();
}
