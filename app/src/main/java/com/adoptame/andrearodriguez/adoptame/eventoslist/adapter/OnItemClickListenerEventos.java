package com.adoptame.andrearodriguez.adoptame.eventoslist.adapter;

import android.widget.ImageView;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public interface OnItemClickListenerEventos {
    void onEventoClick(Eventos eventos);
    void onShareclick(Eventos eventos, ImageView img);
    void onDeleteClick(Eventos eventos);

}
