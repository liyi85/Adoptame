package com.example.andrearodriguez.adoptame.perrodetail.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.andrearodriguez.adoptame.R;
import com.example.andrearodriguez.adoptame.perrodetail.adapter.MainSectionPagerAdapter;
import com.example.andrearodriguez.adoptame.perrodetail.fundacionfragment.FundacionFragment;
import com.example.andrearodriguez.adoptame.perrodetail.perrofragment.PerroFragment;

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
        setupAdapter();

        toolbar.setTitle("perros");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    private void setupAdapter() {
        Fragment[] fragment = new Fragment[]{new PerroFragment(), new FundacionFragment()};
        String[] titles = new String[]{getString(R.string.main_perro_info), getString(R.string.main_fundacion_info)};
        MainSectionPagerAdapter adapter = new MainSectionPagerAdapter(getSupportFragmentManager(), titles, fragment);

        container.setAdapter(adapter);
        tabs.setupWithViewPager(container);
    }
}
