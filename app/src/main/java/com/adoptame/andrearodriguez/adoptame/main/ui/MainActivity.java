package com.adoptame.andrearodriguez.adoptame.main.ui;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.adoptame.andrearodriguez.adoptame.BebeAdoptaApp;
import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.entities.Bebe;
import com.adoptame.andrearodriguez.adoptame.entities.Fundacion;
import com.adoptame.andrearodriguez.adoptame.fundacioneslist.ui.FundationListActivity;
import com.adoptame.andrearodriguez.adoptame.gatolist.ui.GatoListActivity;
import com.adoptame.andrearodriguez.adoptame.main.MainPresenter;
import com.adoptame.andrearodriguez.adoptame.perrolist.ui.PerroListActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.imgPerro)
    Button imgPerro;
    @Bind(R.id.imgCat)
    Button imgCat;
    @Bind(R.id.imgOtro)
    Button imgOtro;
    @Bind(R.id.imgEncontrar)
    Button imgEncontrar;
    @Bind(R.id.main_content)
    RelativeLayout mainContent;


    @Inject
    MainPresenter presenter;
    @Inject
    SharedPreferences sharedPreferences;

    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.imgFundation)
    Button imgFundation;
    @Bind(R.id.imgEventos)
    Button imgAbout;


    private BebeAdoptaApp app;
    private List<Bebe> perroList;

    Fundacion fundacion;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (BebeAdoptaApp) getApplication();
        setupInjection();
        setupNavigation();
        checkStoragePermision();
    }

    private void checkStoragePermision() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

    private void setupNavigation() {

        //forzar y cargar icono en el action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
//        String email = sharedPreferences.getString(app.getEmailKey(), getString(R.string.app_name));
//        getSupportActionBar().setTitle(email);


    }

    private void setupInjection() {
        app.getMainCompoentn().inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @OnClick({R.id.imgPerro, R.id.imgCat, R.id.imgOtro, R.id.imgEncontrar, R.id.imgFundation, R.id.imgEventos})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPerro:
                Intent intent = new Intent(this, PerroListActivity.class);
                startActivity(intent);
                break;
            case R.id.imgCat:
                Intent intentG = new Intent(this, GatoListActivity.class);
                startActivity(intentG);
                break;
            case R.id.imgOtro:
                Toast.makeText(getApplicationContext(), "Estamos trabajando en esta sección", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgEncontrar:
                Toast.makeText(getApplicationContext(), "Estamos trabajando en esta sección", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgFundation:
                Intent intentF = new Intent(this, FundationListActivity.class);
                startActivity(intentF);
                break;
            case R.id.imgEventos:
                Toast.makeText(getApplicationContext(), "Estamos trabajando en esta sección", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
