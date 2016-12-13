package com.example.andrearodriguez.adoptame.fundacioneslist.ui;

import com.example.andrearodriguez.adoptame.entities.Fundacion;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public interface FundationListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addFundation(Fundacion fundacion);
    void onFundationError(String error);


}
