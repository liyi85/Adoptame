package com.adoptame.andrearodriguez.adoptame.perrolist.ui.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 8/22/16.
 */
public interface OnItemClickListener {
    void onPerroClick(Bebe bebe);
    void onShareclick(Bebe bebe, ImageView img, TextView sexo, TextView edad);
    void onDeleteClick(Bebe bebe);

    void onFavClick(Bebe bebe);
}
