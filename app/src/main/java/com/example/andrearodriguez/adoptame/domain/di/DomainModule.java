package com.example.andrearodriguez.adoptame.domain.di;

import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.domain.FirebaseFundacionesAPI;
import com.example.andrearodriguez.adoptame.domain.FirebaseGatosAPI;
import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Module
public class DomainModule {
    String firebaseURL;

    public DomainModule(String firebaseURL) {
        this.firebaseURL = firebaseURL;
    }

    @Provides
    @Singleton
    FirebaseFundacionesAPI providesFirebaseFundacionesAPI (Firebase firebase){
        return new FirebaseFundacionesAPI(firebase);
    }
    @Provides
    @Singleton
    FirebaseGatosAPI providesFirebaseGatosAPI (Firebase firebase){
        return new FirebaseGatosAPI(firebase);
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase){
        return new FirebaseAPI(firebase);
    }

    @Provides
    @Singleton
    Firebase providesFirebase(String firebaseURL){
        return new Firebase(firebaseURL);
    }
    @Provides
    @Singleton
    String providesFirebaseURL(){
        return this.firebaseURL;
    }

}
