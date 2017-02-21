package com.adoptame.andrearodriguez.adoptame.fundacioneslist;

import com.adoptame.andrearodriguez.adoptame.fundacioneslist.events.FundacionListEvent;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.FundationListView;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.perrolist.events.PerroListEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundationListPresenterImp implements FundationListPresenter{

    private EventBus eventBus;
    private FundationListView view;
    private FundationListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public FundationListPresenterImp(EventBus eventBus, FundationListView view, FundationListInteractor interactor) {
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

    @Subscribe (threadMode = ThreadMode.MAIN)
    @Override
    public void onEventMainThread(FundacionListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onFundationError(EMPTY_LIST);
                } else {
                    view.onFundationError(error);
                }
            } else {
                if (event.getType() == PerroListEvent.READ_EVENT) {
                    view.addFundation(event.getFundacion());
                }
            }
        }

    }
}
