package com.example.andrearodriguez.adoptame.perrolist.ui;

import com.example.andrearodriguez.adoptame.entities.Bebe;

import java.util.List;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public interface PerroListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPerro(Bebe bebe);
    void removePerro(Bebe bebe);
    void onPerroError(String error);

    void onPerroUpload();

    void setBebes(List<Bebe> data);


}
