package com.example.andrearodriguez.adoptame.login;

/**
 * Created by andrearodriguez on 7/24/16.
 */
public class LoginInteractorImp implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImp(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void doSignUp(String email, String password) {
        loginRepository.signUp(email, password);

    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.SignIn(email, password);

    }
}