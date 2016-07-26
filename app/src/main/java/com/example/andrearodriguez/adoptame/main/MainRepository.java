package com.example.andrearodriguez.adoptame.main;

/**
 * Created by andrearodriguez on 7/26/16.
 */
public interface MainRepository {
    void logout();
    void uploadBebe(String path, String name, String edad, String tamaño, String sexo, String fundación);
}
