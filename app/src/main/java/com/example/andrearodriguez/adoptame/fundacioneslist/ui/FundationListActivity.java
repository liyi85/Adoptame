package com.example.andrearodriguez.adoptame.fundacioneslist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.entities.Fundacion;
import com.example.andrearodriguez.adoptame.fundacioneslist.FundationListPresenter;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.adapters.FundationListAdapter;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.adapters.OnItemClickListenerF;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public class FundationListActivity extends AppCompatActivity implements FundationListView, OnItemClickListenerF {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.recyclerViewFundation)
    RecyclerView recyclerViewFundation;
    @Bind(R.id.progresBarAddFundation)
    ProgressBar progresBarAddFundation;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Inject
    FundationListAdapter adapter;

    @Inject
    FundationListPresenter presenter;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundation);
        ButterKnife.bind(this);
        setupToolbar();
        setupInjection();
        setupRecyclerView();
        presenter.onCreate();
        presenter.subscribe();

    }

    private void setupRecyclerView() {
        recyclerViewFundation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFundation.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.unsubscribe();
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        BebeAdoptaApp app = (BebeAdoptaApp) getApplication();
        app.getFundationListComponent(this, this, this).inject(this);
    }

    private void setupToolbar() {
        toolbar.setTitle("fundaciones");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    @Override
    public void showList() {
        recyclerViewFundation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        recyclerViewFundation.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progresBarAddFundation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progresBarAddFundation.setVisibility(View.GONE);
    }

    @Override
    public void addFundation(Fundacion fundacion) {
        adapter.addFundation(fundacion);
    }

    @Override
    public void onFundationError(String error) {
        Snackbar.make(mainContent, error, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onFundationEmail(Fundacion fundacion) {
        String email = fundacion.getEmail().toString();
        if (email != null) {
            Intent intenEmail = new Intent(Intent.ACTION_SEND);
            intenEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intenEmail.setType("message/rfc822");
            startActivity(Intent.createChooser(intenEmail, "Elige su cliente de Email :"));
        }
    }


    @Override
    public void onFundationCall(Fundacion fundacion) {
        String phoneNumber = fundacion.getTelefono().toString();

        Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(intentPhone);

//        if (phoneNumber != null) {
//            //comprobar version del telefono vs del programa
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                //verificar si ha aceptado, a denegado o nunca se le ha preguntado
//                if(CheckPermission(Manifest.permission.CALL_PHONE)){
//                    //ha aceptado
//                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//                    if(ActivityCompat.checkSelfPermission(FundationListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED);
//                    startActivity(i);
//                }
//
//                else{
//                    //ha denegado o es la primera vez que se le pregunta
//                    if(!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)){
//                        //no se le pregunta aun
//                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
//                    }else{
//                        //Ha denegado
//                        Toast.makeText(FundationListActivity.this, "Por favor autorice el acceso de la aplicación al teléfono", Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        i.addCategory(Intent.CATEGORY_DEFAULT);
//                        i.setData(Uri.parse("package:" + getPackageName()));
//                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//                        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                        startActivity(i);
//                    }
//                }
//
//            } else {
//                OlderVersions(phoneNumber);
//            }
//
//        }
//    }
//
//    private void OlderVersions(String phoneNumber) {
//        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//        if (CheckPermission(Manifest.permission.CALL_PHONE)) {
//            startActivity(intentCall);
//        } else {
//            Toast.makeText(FundationListActivity.this, "You decline access", Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, Fundacion fundacion) {
//        //Estamos en el caso del telefono
//        switch (requestCode) {
//
//            case PHONE_CALL_CODE:
//
//                String permission = permissions[0];
//                int result = grantResults[0];
//
//                if (permission.equals(Manifest.permission.CALL_PHONE)) {
//                    //comprobar si fue aceptado o negado el permiso
//                    if (result == PackageManager.PERMISSION_GRANTED) {
//                        //acepto el permiso
//                        String phoneNumber = fundacion.getTelefono().toString();
//                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel" + phoneNumber));
//                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                            return;
//                        }
//                        startActivity(intentCall);
//                    }else{
//                        //no dio permiso
//                            Toast.makeText(FundationListActivity.this, "You declined the access", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//
//            default:
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//                break;
//
//        }
//    }
//
//    private boolean CheckPermission(String permission){
//        int result = this.checkCallingOrSelfPermission(permission);
//        return result == PackageManager.PERMISSION_GRANTED;
//    }
    }
}