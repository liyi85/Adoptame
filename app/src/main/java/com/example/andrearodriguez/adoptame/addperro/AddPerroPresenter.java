package com.example.andrearodriguez.adoptame.addperro;

import com.example.andrearodriguez.adoptame.addperro.event.AddPerroEvent;
import com.example.andrearodriguez.adoptame.entities.Bebe;


/**
 * Created by andrearodriguez on 8/17/16.
 */
public interface AddPerroPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String name, String edad, String path);

    void onEventMainThread(AddPerroEvent event);
}
