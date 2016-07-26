package com.example.andrearodriguez.adoptame.signup.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.MainActivity;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.login.LoginPresenter;
import com.example.andrearodriguez.adoptame.login.ui.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity implements LoginView{

    @Bind(R.id.txtEmail)
    EditText txtEmail;
    @Bind(R.id.txtPassword)
    EditText txtPassword;

    @Bind(R.id.txtTelefono)
    EditText txtTelefono;
    @Bind(R.id.txtFundacion)
    EditText txtFundacion;
    @Bind(R.id.txtAddress)
    EditText txtAddress;
    @Bind(R.id.txtRepresentante)
    EditText txtRepresenta;
    @Bind(R.id.txtNit)
    EditText txtNit;

    @Bind(R.id.btnSignUp)
    Button btnSignUp;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer2)
    RelativeLayout container;


    private BebeAdoptaApp app;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        setTitle(R.string.signup_title);
        app = (BebeAdoptaApp)getApplication();
        setupInjection();
        loginPresenter.onCreate();
        loginPresenter.validateLogin(null, null);
    }

    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignUp)
    @Override
    public void handleSignUp() {
        loginPresenter.registerNewUser(txtEmail.getText().toString(),
                                                           txtPassword.getText().toString());
    }

    @Override
    public void handleSignIn() {
        throw new UnsupportedOperationException("Operation is not valid in SignupActivity");
    }

    @Override
    public void navigationToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginError(String error) {
        throw new UnsupportedOperationException("Operation is not valid in SignupActivity");
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(container, R.string.loging_notice_signin, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        txtPassword.setText("");
        String msgError = String.format(getString(R.string.loging_error_messagge_signup, error));
        txtPassword.setError(msgError);
    }

    @Override
    public void setUserEmail(String email) {

    }

    private void setInputs (boolean enabled){
        txtEmail.setEnabled(enabled);
        txtPassword.setEnabled(enabled);
        txtTelefono.setEnabled(enabled);
        txtNit.setEnabled(enabled);
        txtFundacion.setEnabled(enabled);
        txtAddress.setEnabled(enabled);
        txtRepresenta.setEnabled(enabled);
        btnSignUp.setEnabled(enabled);
    }
    private void setupInjection() {
        app.getSignupComponent(this).inject(this);

    }
}
