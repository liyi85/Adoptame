package com.example.andrearodriguez.adoptame.login;


/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void SignIn(String email, String password);
}
