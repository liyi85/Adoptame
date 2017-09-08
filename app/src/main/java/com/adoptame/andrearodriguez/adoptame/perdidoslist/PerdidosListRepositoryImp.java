package com.adoptame.andrearodriguez.adoptame.perdidoslist;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseEventListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebasePerdidosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.perdidoslist.event.PerdidosListEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public class PerdidosListRepositoryImp implements PerdidosListRepository{

    private EventBus eventBus;
    private FirebasePerdidosAPI firebasePerdidosAPI;

    public PerdidosListRepositoryImp(EventBus eventBus, FirebasePerdidosAPI firebasePerdidosAPI) {
        this.eventBus = eventBus;
        this.firebasePerdidosAPI = firebasePerdidosAPI;
    }

    @Override
    public void subscribe() {
        firebasePerdidosAPI.checkForData(new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(PerdidosListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(PerdidosListEvent.READ_EVENT, "");
                }
            }
        });
        firebasePerdidosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                //String email = firebasePerdidosAPI.getAuthEmail();
                bebe.setId(dataSnapshot.getKey());

//                boolean publishedByMy = (bebe.getEmail()).equals(email);
//                bebe.setPublishedByMe(publishedByMy);
                post(PerdidosListEvent.READ_EVENT, bebe);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                bebe.setId(dataSnapshot.getKey());

                post(PerdidosListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(PerdidosListEvent.READ_EVENT, error.getMessage());
            }

        });
    }

    @Override
    public void unsubscribe() {
        firebasePerdidosAPI.unsubscribe();

    }

    @Override
    public void removePerdido(final Bebe bebe) {

        firebasePerdidosAPI.remove(bebe, new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {
                post(PerdidosListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onError(FirebaseError error) {
                post(PerdidosListEvent.DELETE_EVENT, error.getMessage());
            }
        });
    }

    private void post(int type, Bebe bebe) {
        post(type, bebe, null);
    }

    private void post(int type, String error) {
        post(type, null, error);
    }

    private void post(int type, Bebe bebe, String error) {
        PerdidosListEvent event = new PerdidosListEvent();
        event.setType(type);
        event.setError(error);
        event.setBebe(bebe);
        eventBus.post(event);
    }
}
