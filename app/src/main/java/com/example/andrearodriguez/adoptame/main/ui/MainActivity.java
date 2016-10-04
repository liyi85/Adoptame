package com.example.andrearodriguez.adoptame.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableRow;

import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.fundacioneslist.ui.FundationListActivity;
import com.example.andrearodriguez.adoptame.main.MainPresenter;
import com.example.andrearodriguez.adoptame.perrolist.ui.PerroListActivity;

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
    @Bind(R.id.txtarriba)
    TableRow txtarriba;
    @Bind(R.id.arriba)
    TableRow arriba;
    @Bind(R.id.txtabajo)
    TableRow txtabajo;
    @Bind(R.id.abajo)
    TableRow abajo;
    @Bind(R.id.imgAbout)
    Button imgAbout;
    @Bind(R.id.abajo2)
    TableRow abajo2;

    private BebeAdoptaApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (BebeAdoptaApp) getApplication();
        setupInjection();
        setupNavigation();

    }

    private void setupNavigation() {
        setSupportActionBar(toolbar);
        String email = sharedPreferences.getString(app.getEmailKey(), getString(R.string.app_name));
        getSupportActionBar().setTitle(email);

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



    @OnClick({R.id.imgPerro, R.id.imgCat, R.id.imgOtro, R.id.imgEncontrar, R.id.imgFundation })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgPerro:
                Intent intent = new Intent(this, PerroListActivity.class);
                startActivity(intent);
                break;
            case R.id.imgCat:
                break;
            case R.id.imgOtro:
                break;
            case R.id.imgEncontrar:
                break;
            case R.id.imgFundation:
                Intent intentF = new Intent(this, FundationListActivity.class);
                startActivity(intentF);
                break;
            case R.id.imgAbout:
                break;
        }
    }
}
