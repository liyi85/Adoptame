package com.example.andrearodriguez.adoptame.libs.base;

import java.io.File;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}
