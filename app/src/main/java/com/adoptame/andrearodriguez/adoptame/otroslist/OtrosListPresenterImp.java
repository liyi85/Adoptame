package com.adoptame.andrearodriguez.adoptame.otroslist;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.otroslist.event.OtrosListEvent;
import com.adoptame.andrearodriguez.adoptame.otroslist.ui.OtroListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public class OtrosListPresenterImp implements OtrosListPresenter{

    private EventBus eventBus;
    private OtroListView view;
    private OtrosListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public OtrosListPresenterImp(EventBus eventBus, OtroListView view, OtrosListInteractor interactor) {
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
    public void removeOtro(Bebe bebe) {
        interactor.removeOtro(bebe);

    }

    @Subscribe
    @Override
    public void onEventMainThread(OtrosListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onOtroError(EMPTY_LIST);
                } else {
                    view.onOtroError(error);
                }
            } else {
                if (event.getType() == OtrosListEvent.READ_EVENT) {
                    view.addOtro(event.getBebe());
                } else if (event.getType() == OtrosListEvent.DELETE_EVENT) {
                    view.removeOtro(event.getBebe());
                }else if (event.getType() == OtrosListEvent.UPDATE_EVENT){
                    view.onOtroUpload();
                }
            }
        }
    }

    @Override
    public OtroListView getView() {
        return this.view;
    }
}
