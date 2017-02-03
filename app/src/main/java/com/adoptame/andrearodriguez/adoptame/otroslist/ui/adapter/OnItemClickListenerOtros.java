package com.adoptame.andrearodriguez.adoptame.otroslist.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public interface OnItemClickListenerOtros {
    void onOtroClick(Bebe bebe);
    void onShareclick(Bebe bebe, ImageView img, TextView sexo, TextView edad);
    void onDeleteClick(Bebe bebe);

    void onFavClick(Bebe bebe);
}
