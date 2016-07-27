package com.example.andrearodriguez.adoptame.login;

import com.example.andrearodriguez.adoptame.login.events.LoginEvent;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void onPause();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
