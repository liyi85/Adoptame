package com.example.andrearodriguez.adoptame.domain;

import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface FirebaActionListenerCallback {
    void onSucces();
    void onError(FirebaseError error);
}
