package com.adoptame.andrearodriguez.adoptame.domain;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by andrearodriguez on 2/2/17.
 */

public class FirebaseOtrosAPI {
    private Firebase firebase;
    private ChildEventListener otrosEventListener;

    public FirebaseOtrosAPI(Firebase firebase) {
        this.firebase = firebase.child("Otros");
    }

    public void checkForData(final FirebaActionListenerCallback listenerCallback){
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0){
                    listenerCallback.onSucces();
                }else {
                    listenerCallback.onError(null);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }
    public void subscribe(final FirebaseEventListenerCallback listenerCallback){
        if (otrosEventListener == null){
            otrosEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listenerCallback.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listenerCallback.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    listenerCallback.onCancelled(firebaseError);
                }
            };
            firebase.addChildEventListener(otrosEventListener);
        }
    }
    public void unsubscribe(){
        if (otrosEventListener != null) {
            firebase.removeEventListener(otrosEventListener);
        }
    }
    public String create(){
        return firebase.push().getKey();
    }



    public void update(Bebe bebe){
        this.firebase.child(bebe.getId()).setValue(bebe);

    }

    public void remove (Bebe bebe, FirebaActionListenerCallback listenerCallback){
        this.firebase.child(bebe.getId()).removeValue();
        listenerCallback.onSucces();
    }

    public String getAuthEmail(){
        String email = null;

        if (firebase.getAuth() != null){
            Map<String, Object> providerData = firebase.getAuth().getProviderData();
            email = providerData.get("email").toString();
        }
        return email;

    }
}