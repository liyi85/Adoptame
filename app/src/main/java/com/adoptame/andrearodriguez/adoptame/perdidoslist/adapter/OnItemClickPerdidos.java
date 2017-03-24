package com.adoptame.andrearodriguez.adoptame.perdidoslist.adapter;

import android.widget.ImageView;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface OnItemClickPerdidos {
    void onPerdidoslick(Bebe bebe);
    void onShareclick(Bebe bebe, ImageView img);
    void onDeleteClick(Bebe bebe);
}
