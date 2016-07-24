package com.example.andrearodriguez.adoptame.libs.base;

import android.widget.ImageView;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);
    void setOnFinishedImageLoadingListener(Object listener);
}
