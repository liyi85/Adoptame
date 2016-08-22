package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.perrolist.events.PerroListEvent;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String path);
    void onEventMainThread(PerroListEvent event);
}
