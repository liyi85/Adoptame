package com.example.andrearodriguez.adoptame.perrolist;


import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.perrolist.events.PerroListEvent;
import com.example.andrearodriguez.adoptame.perrolist.ui.PerroListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public class PerroListPresenterImp implements PerroListPresenter{

    private EventBus eventBus;
    private PerroListView view;
    private PerroListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public PerroListPresenterImp(EventBus eventBus, PerroListView view, PerroListInteractor interactor) {
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
    public void removePerro(Bebe bebe) {
        interactor.removePerro(bebe);

    }

    @Override
    @Subscribe
    public void onEventMainThread(PerroListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onPerroError(EMPTY_LIST);
                } else {
                    view.onPerroError(error);
                }
            } else {
                if (event.getType() == PerroListEvent.READ_EVENT) {
                    view.addPerro(event.getBebe());
                } else if (event.getType() == PerroListEvent.DELETE_EVENT) {
                    view.removePerro(event.getBebe());
                }else if (event.getType() == PerroListEvent.UPDATE_EVENT){
                    view.onPerroUpload();
                }
            }
        }

    }

    @Override
    public PerroListView getView() {
        return this.view;
    }

}
