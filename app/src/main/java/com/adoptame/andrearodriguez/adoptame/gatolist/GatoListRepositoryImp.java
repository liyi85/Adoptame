package com.adoptame.andrearodriguez.adoptame.gatolist;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseEventListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseGatosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.gatolist.event.GatoListEvent;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.List;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public class GatoListRepositoryImp implements GatoListRepository{

    private EventBus eventBus;
    private FirebaseGatosAPI firebaseGatosAPI;

    public GatoListRepositoryImp(EventBus eventBus, FirebaseGatosAPI firebaseGatosAPI) {
        this.eventBus = eventBus;
        this.firebaseGatosAPI = firebaseGatosAPI;
    }

    @Override
    public void subscribe() {
        firebaseGatosAPI.checkForData(new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(GatoListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(GatoListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseGatosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                String email = firebaseGatosAPI.getAuthEmail();
                bebe.setId(dataSnapshot.getKey());
//
//                boolean publishedByMy = (bebe.getEmail()).equals(email);
//                bebe.setPublishedByMe(publishedByMy);
                post(GatoListEvent.READ_EVENT, bebe);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                bebe.setId(dataSnapshot.getKey());

                post(GatoListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(GatoListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseGatosAPI.unsubscribe();
    }

    @Override
    public void removeGato(final Bebe bebe) {
        firebaseGatosAPI.remove(bebe, new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {
                post(GatoListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onError(FirebaseError error) {
                post(GatoListEvent.DELETE_EVENT, error.getMessage());
            }
        });

    }

    @Override
    public void updateGato(Bebe bebe) {
        bebe.update();
        post();
    }


    private void post(int type, Bebe bebe) {
        post(type, bebe, null);
    }

    private void post(int type, String error) {
        post(type, null, error);
    }

    private void post(int type, Bebe bebe, String error) {
        GatoListEvent event = new GatoListEvent();
        event.setType(type);
        event.setError(error);
        event.setBebe(bebe);
        eventBus.post(event);
    }

    private void post () {
        post(GatoListEvent.UPDATE_EVENT, null, null);

    }

    private void post(int type, List<Bebe> bebes){
        GatoListEvent event = new GatoListEvent();
        event.setBebeList(bebes);
        event.setType(type);
        eventBus.post(event);
    }
}
