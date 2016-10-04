package com.example.andrearodriguez.adoptame.fundacioneslist;

import com.example.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.example.andrearodriguez.adoptame.domain.FirebaseEventListenerCallback;
import com.example.andrearodriguez.adoptame.domain.FirebaseFundacionesAPI;
import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.example.andrearodriguez.adoptame.fundacioneslist.events.FundacionListEvent;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundationListRepositoryImp implements FundationListRepository{

    private EventBus eventBus;
    private FirebaseFundacionesAPI firebaseFundationAPI;


    public FundationListRepositoryImp(EventBus eventBus, FirebaseFundacionesAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseFundationAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseFundationAPI.checkForData(new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(FundacionListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(FundacionListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseFundationAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Fundacion fundacion = dataSnapshot.getValue(Fundacion.class);
                String email = firebaseFundationAPI.getAuthEmail();
                fundacion.setId(dataSnapshot.getKey());


                post(FundacionListEvent.READ_EVENT, fundacion);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(FundacionListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unsubscribe() {
        firebaseFundationAPI.unsubscribe();

    }


    private void post(int type, Fundacion fundacion) {
        post(type, fundacion, null);
    }

    private void post(int type, String error) {
        post(type, null, error);
    }

    private void post(int type, Fundacion fundacion, String error) {
        FundacionListEvent event = new FundacionListEvent();
        event.setType(type);
        event.setError(error);
        event.setFundacion(fundacion);
        eventBus.post(event);
    }
}
