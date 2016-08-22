package com.example.andrearodriguez.adoptame.main;

import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;

/**
 * Created by andrearodriguez on 8/11/16.
 */
public class MainRepositoryImp implements MainRepository{

    private FirebaseAPI firebaseAPI;

    public MainRepositoryImp(FirebaseAPI firebaseAPI) {
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void logout() {
        firebaseAPI.logout();
    }
}
