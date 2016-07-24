package com.example.andrearodriguez.adoptame.login;


import com.example.andrearodriguez.adoptame.domain.FirebaActionListenerCallback;
import com.example.andrearodriguez.adoptame.domain.FirebaseAPI;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.login.events.LoginEvent;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public class LoginRepositoryImp implements LoginRepository {

    private EventBus eventBus;
    private FirebaseAPI firebaseAPI;

    public LoginRepositoryImp(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void signUp(final String email, final String password) {
        firebaseAPI.signup(email, password, new FirebaActionListenerCallback() {
            @Override
            public void onSucces() {
                postEvent(LoginEvent.onSignUpSuccess);
                SignIn(email, password);
            }

            @Override
            public void onError(FirebaseError error) {
                postEvent(LoginEvent.onSignUpError, error.getMessage(), null);
            }

        });
    }

    @Override
    public void SignIn(String email, String password) {
        if(email != null && password!=null){
            firebaseAPI.login(email, password, new FirebaActionListenerCallback() {

                @Override
                public void onSucces() {
                    String email = firebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.onSignInError, error.getMessage(), null);
                }
            });
        }else{
            firebaseAPI.checkForSession(new FirebaActionListenerCallback() {
                @Override
                public void onSucces() {
                    String email = firebaseAPI.getAuthEmail();
                    postEvent(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.onFailedRecoverSession);
                }
            });
        }
    }

    private void postEvent(int type, String errorMessage, String currentUserEmail){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setCurrenUserEmail(currentUserEmail);
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null, null);
    }

    private void postEvent(int type, String currentUserEmail){
        postEvent(type, null, currentUserEmail);
    }
}
