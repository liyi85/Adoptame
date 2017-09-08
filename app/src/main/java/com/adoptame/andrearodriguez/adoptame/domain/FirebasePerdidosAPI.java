package com.adoptame.andrearodriguez.adoptame.domain;

import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public class FirebasePerdidosAPI {

    private Firebase firebase;
    private ChildEventListener perdidosEventListener;

    public FirebasePerdidosAPI(Firebase firebase) {
        this.firebase = firebase.child("Perdidos");
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
        if (perdidosEventListener == null){
            perdidosEventListener = new ChildEventListener() {
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
            firebase.addChildEventListener(perdidosEventListener);
        }
    }
    public void unsubscribe(){
        if (perdidosEventListener != null) {
            firebase.removeEventListener(perdidosEventListener);
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

//    public String getAuthEmail(){
//        String email = null;
//
//        if (firebase.getAuth() != null){
//            Map<String, Object> providerData = firebase.getAuth().getProviderData();
//            email = providerData.get("email").toString();
//        }
//        return email;
//
//    }
}
