package com.adoptame.andrearodriguez.adoptame.otroslist.event;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

import java.util.List;

/**
 * Created by andrearodriguez on 2/2/17.
 */
public class OtrosListEvent {
    private int type;
    private Bebe bebe;
    private String error;
    private List<Bebe> bebeList;

    public final static int READ_EVENT = 0;
    public final static int DELETE_EVENT = 1;
    public final static int UPDATE_EVENT=2;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Bebe getBebe() {
        return bebe;
    }

    public void setBebe(Bebe bebe) {
        this.bebe = bebe;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Bebe> getBebeList() {
        return bebeList;
    }

    public void setBebeList(List<Bebe> bebeList) {
        this.bebeList = bebeList;
    }
}
