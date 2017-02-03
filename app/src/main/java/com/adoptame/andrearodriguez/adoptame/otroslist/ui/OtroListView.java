package com.adoptame.andrearodriguez.adoptame.otroslist.ui;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;

/**
 * Created by andrearodriguez on 2/2/17.
 */
public interface OtroListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addOtro(Bebe bebe);
    void removeOtro(Bebe bebe);
    void onOtroError(String error);

    void onOtroUpload();
}
