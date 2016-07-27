package com.example.andrearodriguez.adoptame.main.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andrearodriguez.adoptame.BebeAdoptaApp;
import com.example.andrearodriguez.adoptame.EncuentraListFragment;
import com.example.andrearodriguez.adoptame.GatoListFragment;
import com.example.andrearodriguez.adoptame.OtrosListFragment;
import com.example.andrearodriguez.adoptame.PerroListFragment;
import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.login.ui.LoginActivity;
import com.example.andrearodriguez.adoptame.main.MainPresenter;
import com.example.andrearodriguez.adoptame.main.events.MainEvent;
import com.example.andrearodriguez.adoptame.main.ui.adapter.MainSectionPagerAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainView{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    MainPresenter presenter;
    @Inject
    MainSectionPagerAdapter adapter;
    @Inject
    SharedPreferences sharedPreferences;

    private String photoPath;
    private BebeAdoptaApp app;

    private static final int REQUEST_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        app = (BebeAdoptaApp) getApplication();
        setupInjection();
        setupNavigation();

        presenter.onCreate();
    }

    private void setupNavigation() {
        String email = sharedPreferences.getString(app.getEmailKey(), getString(R.string.app_name));
        toolbar.setTitle(email);
        setSupportActionBar(toolbar);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupInjection() {
        String[] titles = new String[]{getString(R.string.main_titles_perros),
                                        getString(R.string.main_title_gatos),
                                        getString(R.string.main_title_otros),
                                        getString(R.string.main_title_perdidos)};

        Fragment[] fragments = new Fragment[]{new PerroListFragment(),
                                                new GatoListFragment(),
                                                new OtrosListFragment(),
                                                new EncuentraListFragment()};


        adapter = new MainSectionPagerAdapter(getSupportFragmentManager(), titles, fragments);
        sharedPreferences = getSharedPreferences(app.getSharedPreferencesName(), MODE_PRIVATE);
        presenter = new MainPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void logout() {

            }

            @Override
            public void uploadPhoto(String path, String name, String edad, String tamaño, String sexo, String fundación) {

            }

            @Override
            public void onEventMainThread(MainEvent event) {

            }
        };
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        presenter.logout();
        sharedPreferences.edit().clear().commit();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onUploadInit() {

    }

    @Override
    public void onUploadComplete() {

    }

    @Override
    public void onUploadError(String error) {

    }
}
