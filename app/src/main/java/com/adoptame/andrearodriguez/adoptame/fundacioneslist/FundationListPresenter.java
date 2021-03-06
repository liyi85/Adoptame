package com.adoptame.andrearodriguez.adoptame.fundacioneslist;

import com.adoptame.andrearodriguez.adoptame.fundacioneslist.events.FundacionListEvent;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public interface FundationListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void onEventMainThread(FundacionListEvent event);
}
