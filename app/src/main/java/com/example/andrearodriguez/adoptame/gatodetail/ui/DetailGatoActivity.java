package com.example.andrearodriguez.adoptame.gatodetail.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.gatodetail.adapter.PagerAdapterGatos;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailGatoActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.container)
    ViewPager container;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gato);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.main_title_gatos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabs.addTab(tabs.newTab().setText(R.string.main_perro_info));
        tabs.addTab(tabs.newTab().setText(R.string.main_fundacion_info));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapterGatos adapter = new PagerAdapterGatos
                (getSupportFragmentManager(), tabs.getTabCount());
        container.setAdapter(adapter);
        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                container.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });





    }

}
