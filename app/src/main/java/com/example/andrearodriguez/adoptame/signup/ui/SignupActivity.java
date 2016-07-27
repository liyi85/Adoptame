package com.example.andrearodriguez.adoptame.signup.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.example.andrearodriguez.adoptame.main.ui.MainActivity;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.login.LoginPresenter;
import com.example.andrearodriguez.adoptame.login.ui.LoginView;
import com.firebase.client.Config;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity implements LoginView {

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

    @Bind(R.id.wraperphone)
    TextInputLayout wraperphone;
    @Bind(R.id.wraperfundacion)
    TextInputLayout wraperfundacion;
    @Bind(R.id.wraperemail)
    TextInputLayout wraperemail;
    @Bind(R.id.wraperpassword)
    TextInputLayout wraperpassword;
    @Bind(R.id.wrapernit)
    TextInputLayout wrapernit;
    @Bind(R.id.wraperaddress)
    TextInputLayout wraperaddress;
    @Bind(R.id.wraperrepresentante)
    TextInputLayout wraperrepresentante;

    @Bind(R.id.btnSignUp)
    Button btnSignUp;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.layoutMainContainer2)
    RelativeLayout container;

    Firebase ref;


    private BebeAdoptaApp app;


    @Inject
    LoginPresenter loginPresenter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        setTitle(R.string.signup_title);
        app = (BebeAdoptaApp) getApplication();
        setupInjection();
        loginPresenter.onCreate();


        Firebase.setAndroidContext(this);
        ref = new Firebase("https://adoptameapp.firebaseIO.com");

        txtEmail.addTextChangedListener(new MyTextWatcher(txtEmail));
        txtPassword.addTextChangedListener(new MyTextWatcher(txtPassword));
        txtRepresenta.addTextChangedListener(new MyTextWatcher(txtRepresenta));
        txtNit.addTextChangedListener(new MyTextWatcher(txtNit));
        txtAddress.addTextChangedListener(new MyTextWatcher(txtAddress));
        txtFundacion.addTextChangedListener(new MyTextWatcher(txtFundacion));
        txtTelefono.addTextChangedListener(new MyTextWatcher(txtTelefono));

    }
    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.onResume();

    }

    @Override
    protected void onPause() {
        loginPresenter.onPause();
        super.onPause();
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
        submitForm();
        agregandoFirebas();
    }


    private void submitForm() {
        if (!validateRepresentante()) {
            return;
        }
        if (!validateFundacion()) {
            return;
        }

        if (!validateDireccion()) {
            return;
        }
        if (!validateTelefono()) {
            return;
        }
        if (!validateNit()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Snackbar.make(container, R.string.signup_registro_completo, Snackbar.LENGTH_SHORT).show();
        loginPresenter.registerNewUser(txtEmail.getText().toString(),
                txtPassword.getText().toString());
    }




    private boolean validateFundacion() {
        if (txtFundacion.getText().toString().trim().isEmpty()) {
            wraperfundacion.setError(getString(R.string.err_msg_name));
            requestFocus(txtFundacion);
            return false;
        } else {
            wraperfundacion.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {

        if (txtEmail.getText().toString().trim().isEmpty() || !isValidEmail(txtEmail.getText().toString().trim())) {
            wraperemail.setError(getString(R.string.err_msg_email));
            requestFocus(txtEmail);
            return false;
        } else {
            wraperemail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTelefono() {
        if (txtTelefono.getText().toString().trim().isEmpty()) {
            wraperphone.setError(getString(R.string.err_msg_phone));
            requestFocus(txtTelefono);
            return false;
        } else {
            wraperphone.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (txtPassword.getText().toString().trim().isEmpty()) {
            wraperpassword.setError(getString(R.string.err_msg_password));
            requestFocus(txtPassword);
            return false;
        } else {
            wraperpassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNit() {
        if (txtNit.getText().toString().trim().isEmpty()) {
            wrapernit.setError(getString(R.string.err_msg_nit));
            requestFocus(txtNit);
            return false;
        } else {
            wrapernit.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateDireccion() {
        if (txtAddress.getText().toString().trim().isEmpty()) {
            wraperaddress.setError(getString(R.string.err_msg_direccion));
            requestFocus(txtAddress);
            return false;
        } else {
            wraperaddress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRepresentante() {
        if (txtRepresenta.getText().toString().trim().isEmpty()) {
            wraperrepresentante.setError(getString(R.string.err_msg_representante));
            requestFocus(txtRepresenta);
            return false;
        } else {
            wraperrepresentante.setErrorEnabled(false);
        }

        return true;
    }

    private  void agregandoFirebas(){

        String nombrefundacion =   txtFundacion.getText().toString().trim();

    ref.child("users").child(nombrefundacion).child("nombre fundacion").setValue(nombrefundacion, new Firebase.CompletionListener() {


        @Override
        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
            if (firebaseError != null) {
                Snackbar.make(container, "intente de nuevo", Snackbar.LENGTH_SHORT).show();
            }

        }


    });
        String representante = txtRepresenta.getText().toString().trim();

        ref.child("users").child(nombrefundacion).child("representante").setValue(representante, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError error, Firebase firebase) {
                if (error != null) {
                    Snackbar.make(container, "intente de nuevo", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        String direccion = txtAddress.getText().toString().trim();

        ref.child("users").child(nombrefundacion).child("direccion").setValue(direccion, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError error, Firebase firebase) {
                if (error != null) {
                    Snackbar.make(container, "intente de nuevo", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
        String telefono = txtTelefono.getText().toString().trim();

        ref.child("users").child(nombrefundacion).child("telefono").setValue(telefono, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError error, Firebase firebase) {
                if (error != null) {
                    Snackbar.make(container, "intente de nuevo", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        String nit = txtNit.getText().toString().trim();

    ref.child("users").child(nombrefundacion).child("nit").setValue(nit, new Firebase.CompletionListener() {
        @Override
        public void onComplete(FirebaseError error, Firebase firebase) {
            if (error != null) {
                Snackbar.make(container, "intente de nuevo", Snackbar.LENGTH_SHORT).show();
            }

        }
    });
        String correo = txtEmail.getText().toString().trim();

        ref.child("users").child(nombrefundacion).child("correo").setValue(correo, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError error, Firebase firebase) {
                if (error != null) {
                    Snackbar.make(container, "intente de nuevo", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

}



    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }

    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.txtFundacion:
                    validateFundacion();
                    break;
                case R.id.txtEmail:
                    validateEmail();
                    break;
                case R.id.txtPassword:
                    validatePassword();
                    break;
                case R.id.txtAddress:
                    validateDireccion();
                    break;
                case R.id.txtNit:
                    validateNit();
                    break;
                case R.id.txtTelefono:
                    validateTelefono();
                    break;
                case R.id.txtRepresentante:
                    validateRepresentante();
                    break;
            }
        }
    }


    @Override
    public void handleSignIn() {
        throw new UnsupportedOperationException("Operation is not valid in SignupActivity");
    }

    @Override
    public void navigationToMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

    private void setInputs(boolean enabled) {
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
