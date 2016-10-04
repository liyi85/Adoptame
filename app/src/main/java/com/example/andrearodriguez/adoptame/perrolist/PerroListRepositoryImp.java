package com.example.andrearodriguez.adoptame.perrolist;

import com.example.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.domain.FirebaseEventListenerCallback;
import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.libs.ClaudinaryImageStorage;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.perrolist.events.PerroListEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public class PerroListRepositoryImp implements PerroListRepository{

    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;


    public PerroListRepositoryImp(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(PerroListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(PerroListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                String email = firebaseAPI.getAuthEmail();
                bebe.setId(dataSnapshot.getKey());

                boolean publishedByMy = (bebe.getEmail()).equals(email);
                bebe.setPublishedByMe(publishedByMy);
                post(PerroListEvent.READ_EVENT, bebe);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Bebe bebe = dataSnapshot.getValue(Bebe.class);
                bebe.setId(dataSnapshot.getKey());

                post(PerroListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(PerroListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();

    }

    @Override
    public void removePerro(final Bebe bebe) {
        firebaseAPI.remove(bebe, new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {
                post(PerroListEvent.DELETE_EVENT, bebe);
            }

            @Override
            public void onError(FirebaseError error) {
                post(PerroListEvent.DELETE_EVENT, error.getMessage());
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
        PerroListEvent event = new PerroListEvent();
        event.setType(type);
        event.setError(error);
        event.setBebe(bebe);
        eventBus.post(event);
    }
}
