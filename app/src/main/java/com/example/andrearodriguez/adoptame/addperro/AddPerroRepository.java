package com.example.andrearodriguez.adoptame.addperro;

import com.example.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/17/16.
 */
public interface AddPerroRepository {
    void uploadPhoto(String name, String edad, String path);
}
