package com.example.andrearodriguez.adoptame.main.ui;

/**
 * Created by andrearodriguez on 7/26/16.
 */
public interface MainView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
