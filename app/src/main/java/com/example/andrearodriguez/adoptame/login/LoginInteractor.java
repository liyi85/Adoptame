package com.example.andrearodriguez.adoptame.login;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface LoginInteractor {
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
