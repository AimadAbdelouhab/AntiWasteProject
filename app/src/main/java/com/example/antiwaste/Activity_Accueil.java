package com.example.antiwaste;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Activity_Accueil extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TabAccueilFragmentAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

        adapter=new TabAccueilFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Liste_Panier(),"Les Paniers");
        adapter.addFragment(new Fragment_Liste_Commercants(),"Les Commerces");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }


    public void onClickFiltrer(View view) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment_Filtrer fragment_filtrer = Fragment_Filtrer.newInstance("Filtrer");
//        fragment_filtrer.show(fragmentManager,"filtrer");

    }
}