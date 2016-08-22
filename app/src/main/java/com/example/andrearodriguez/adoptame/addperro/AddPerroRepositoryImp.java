package com.example.andrearodriguez.adoptame.addperro;

import com.example.andrearodriguez.adoptame.addperro.event.AddPerroEvent;
import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.libs.base.ImageStorage;
import com.example.andrearodriguez.adoptame.libs.base.ImageStorageFinishedListener;

import java.io.File;

/**
 * Created by andrearodriguez on 8/17/16.
 */
public class AddPerroRepositoryImp implements AddPerroRepository{
    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;
    private ImageStorage imageStorage;

    public AddPerroRepositoryImp(EventBus eventBus, FirebaseAPI firebaseAPI, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
        this.imageStorage = imageStorage;
    }

    @Override
    public void uploadPhoto(final String name, final String edad, String path) {
        final String newPhotoId = firebaseAPI.create();
        final Bebe bebe = new Bebe();
        bebe.setId(newPhotoId);
        bebe.setEmail(firebaseAPI.getAuthEmail());

        post(AddPerroEvent.UPLOAD_INIT);

        ImageStorageFinishedListener listener = new ImageStorageFinishedListener() {

            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(newPhotoId);
                bebe.setUrl(url);
                bebe.setName(name);
                bebe.setEdad(edad);
                firebaseAPI.update(bebe);
                post(AddPerroEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(AddPerroEvent.UPLOAD_ERROR, error);
            }
        };
        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type, null);
    }
    private  void post(int type, String error){
        AddPerroEvent event = new AddPerroEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
