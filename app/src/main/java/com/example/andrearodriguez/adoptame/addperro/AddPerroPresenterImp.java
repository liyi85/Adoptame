package com.example.andrearodriguez.adoptame.addperro;

import com.example.andrearodriguez.adoptame.addperro.event.AddPerroEvent;
import com.example.andrearodriguez.adoptame.addperro.ui.AddPerroView;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by andrearodriguez on 8/17/16.
 */
public class AddPerroPresenterImp implements AddPerroPresenter{

    private AddPerroView view;
    private EventBus eventBus;
    private AddPerroInteractor interactor;

    public AddPerroPresenterImp(AddPerroView vie, EventBus eventBus, AddPerroInteractor interactor) {
        this.view = vie;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);

    }

    @Override
    public void uploadPhoto(String name, String edad, String path) {
        interactor.excecute(name, edad, path);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddPerroEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddPerroEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddPerroEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddPerroEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }

}
