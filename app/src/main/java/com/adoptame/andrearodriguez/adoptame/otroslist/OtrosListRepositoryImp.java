package com.adoptame.andrearodriguez.adoptame.otroslist;

import com.adoptame.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseEventListenerCallback;
import com.adoptame.andrearodriguez.adoptame.domain.FirebaseOtrosAPI;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.libs.base.EventBus;
import com.adoptame.andrearodriguez.adoptame.otroslist.event.OtrosListEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.List;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public class OtrosListRepositoryImp implements OtrosListRepository{

    private EventBus eventBus;
    private FirebaseOtrosAPI firebaseOtrosAPI;

    public OtrosListRepositoryImp(EventBus eventBus, FirebaseOtrosAPI firebaseOtrosAPI) {
        this.eventBus = eventBus;
        this.firebaseOtrosAPI = firebaseOtrosAPI;
    }

    @Override
    public void subscribe() {
        firebaseOtrosAPI.checkForData(new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(OtrosListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(OtrosListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseOtrosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                String email = firebaseOtrosAPI.getAuthEmail();
                bebe.setId(dataSnapshot.getKey());

                post(OtrosListEvent.READ_EVENT, bebe);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                bebe.setId(dataSnapshot.getKey());

                post(OtrosListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(OtrosListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseOtrosAPI.unsubscribe();

    }

    @Override
    public void removeOtro(final Bebe bebe) {
        firebaseOtrosAPI.remove(bebe, new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {
                post(OtrosListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onError(FirebaseError error) {
                post(OtrosListEvent.DELETE_EVENT, error.getMessage());
            }
        });

    }

    @Override
    public void updateOtro(Bebe bebe) {
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
        OtrosListEvent event = new OtrosListEvent();
        event.setType(type);
        event.setError(error);
        event.setBebe(bebe);
        eventBus.post(event);
    }

    private void post () {
        post(OtrosListEvent.UPDATE_EVENT, null, null);

    }

    private void post(int type, List<Bebe> bebes){
        OtrosListEvent event = new OtrosListEvent();
        event.setBebeList(bebes);
        event.setType(type);
        eventBus.post(event);
    }

}
