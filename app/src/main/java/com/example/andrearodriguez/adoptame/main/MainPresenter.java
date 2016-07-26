package com.example.andrearodriguez.adoptame.main;

import com.example.andrearodriguez.adoptame.main.events.MainEvent;

/**
 * Created by andrearodriguez on 7/26/16.
 */
public interface MainPresenter {
    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(String path, String name, String edad, String tamaño, String sexo, String fundación);

    void onEventMainThread(MainEvent event);
}
