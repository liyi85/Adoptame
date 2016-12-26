package com.adoptame.andrearodriguez.adoptame.perrodetail.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.adoptame.andrearodriguez.adoptame.R;
import com.adoptame.andrearodriguez.adoptame.perrodetail.adapter.PagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailPerroActivity extends AppCompatActivity {



    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.container)
    ViewPager container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perro);
        ButterKnife.bind(this);


        toolbar.setTitle(R.string.main_titles_perros);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tabs.addTab(tabs.newTab().setText(R.string.main_perro_info));
        tabs.addTab(tabs.newTab().setText(R.string.main_fundacion_info));
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
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
