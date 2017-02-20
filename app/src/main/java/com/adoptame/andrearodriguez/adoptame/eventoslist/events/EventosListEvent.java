package com.adoptame.andrearodriguez.adoptame.eventoslist.events;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;

import java.util.List;

/**
 * Created by andrearodriguez on 2/19/17.
 */
public class EventosListEvent {
    private int type;
    private Eventos eventos;
    private String error;
    private List<Eventos> eventosList;

    public final static int READ_EVENT = 0;
    public final static int DELETE_EVENT = 1;
    public final static int UPDATE_EVENT=2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Eventos> getEventosList() {
        return eventosList;
    }

    public void setEventosList(List<Eventos> eventosList) {
        this.eventosList = eventosList;
    }
}
