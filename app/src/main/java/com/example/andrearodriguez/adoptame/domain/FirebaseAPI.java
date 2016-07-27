package com.example.andrearodriguez.adoptame.domain;

import com.example.andrearodriguez.adoptame.entities.Bebe;
import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public class FirebaseAPI {

    private Firebase firebase;
    private ChildEventListener bebesEventListener;



    public FirebaseAPI(Firebase firebase) {
        this.firebase = firebase;
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
        if (bebesEventListener == null){
            bebesEventListener = new ChildEventListener() {
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
            firebase.addChildEventListener(bebesEventListener);
        }
    }
    public void unsubscribe(){
        if (bebesEventListener != null) {
            firebase.removeEventListener(bebesEventListener);
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
    public void logout(){
        firebase.unauth();
    }
    public void login(String email, String password, final FirebaActionListenerCallback listenerCallback){
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listenerCallback.onSucces();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }
    public void signup(String email, String password, final FirebaActionListenerCallback listenerCallback){
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                listenerCallback.onSucces();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }
    public void checkForSession(FirebaActionListenerCallback listenerCallback){
        if (firebase.getAuth() != null){
            listenerCallback.onSucces();
        }else {
            listenerCallback.onError(null);
        }
    }
}
