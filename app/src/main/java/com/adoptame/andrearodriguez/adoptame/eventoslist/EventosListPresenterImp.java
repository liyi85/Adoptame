package com.adoptame.andrearodriguez.adoptame.eventoslist;

import com.adoptame.andrearodriguez.adoptame.entities.Eventos;
import com.adoptame.andrearodriguez.adoptame.eventoslist.events.EventosListEvent;
import com.adoptame.andrearodriguez.adoptame.eventoslist.ui.EventoListView;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by andrearodriguez on 2/19/17.
 */

public class EventosListPresenterImp implements EventosListPresenter{

    private EventBus eventBus;
    private EventoListView view;
    private EventosListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public EventosListPresenterImp(EventBus eventBus, EventoListView view, EventosListInteractor interactor) {
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
    public void removeEvento(Eventos eventos) {
        interactor.removeEvento(eventos);
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    @Override
    public void onEventMainThread(EventosListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onEventoError(EMPTY_LIST);
                } else {
                    view.onEventoError(error);
                }
            } else {
                if (event.getType() == EventosListEvent.READ_EVENT) {
                    view.addEvento(event.getEventos());
                } else if (event.getType() == EventosListEvent.DELETE_EVENT) {
                    view.removeEvento(event.getEventos());
                }else if (event.getType() == EventosListEvent.UPDATE_EVENT){
                    view.onEventoUpload();
                }
            }
        }
    }

    @Override
    public EventoListView getView() {
        return this.view;
    }
}
