package com.adoptame.andrearodriguez.adoptame.gatolist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.gatolist.event.GatoListEvent;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListView;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public class GatoListPresenterImp implements GatoListPresenter{

    private EventBus eventBus;
    private GatoListView view;
    private GatoListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public GatoListPresenterImp(EventBus eventBus, GatoListView view, GatoListInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void subscribe() {
        if (view != null){
            view.hideList();
            view.showProgress();
        }
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();

    }

    @Override
    public void removeGato(Bebe bebe) {
        interactor.removeGato(bebe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(GatoListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onGatoError(EMPTY_LIST);
                } else {
                    view.onGatoError(error);
                }
            } else {
                if (event.getType() == GatoListEvent.READ_EVENT) {
                    view.addGato(event.getBebe());
                } else if (event.getType() == GatoListEvent.DELETE_EVENT) {
                    view.removeGato(event.getBebe());
                }else if (event.getType() == GatoListEvent.UPDATE_EVENT){
                    view.onGatoUpload();
                }
            }
        }
    }


    @Override
    public GatoListView getView() {
        return this.view;
    }
}
