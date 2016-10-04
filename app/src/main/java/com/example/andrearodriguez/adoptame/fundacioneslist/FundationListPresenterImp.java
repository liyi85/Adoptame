package com.example.andrearodriguez.adoptame.fundacioneslist;

import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.example.andrearodriguez.adoptame.fundacioneslist.events.FundacionListEvent;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public interface FundationListPresenterImp {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePerro(Fundacion fundacion);
    void onEventMainThread(FundacionListEvent event);
}
