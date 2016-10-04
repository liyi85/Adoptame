package com.example.andrearodriguez.adoptame.fundacioneslist.events;

import com.example.andrearodriguez.adoptame.entities.Fundacion;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundacionListEvent {

    private int type;
    private Fundacion fundacion;
    private String error;

    public final static int READ_EVENT = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Fundacion getFundacion() {
        return fundacion;
    }

    public void setFundacion(Fundacion fundacion) {
        this.fundacion = fundacion;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
