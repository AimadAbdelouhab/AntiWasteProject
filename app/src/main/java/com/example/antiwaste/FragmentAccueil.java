package com.example.antiwaste;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAccueil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAccueil extends Fragment {

    TextView tvfiltrer;

    TabLayout tabLayout;
    ViewPager viewPager;
    TabAccueilFragmentAdapter adapter;
    private FragmentActivity myContext;



    public FragmentAccueil() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentAccueil newInstance(String param1, String param2) {
        FragmentAccueil fragment = new FragmentAccueil();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);
        FragmentManager fragManager = myContext.getSupportFragmentManager();

        adapter=new TabAccueilFragmentAdapter(getChildFragmentManager());
        adapter.addFragment(new Fragment_Liste_Panier(),"Les Paniers");
        adapter.addFragment(new Fragment_Liste_Commercants(),"Les Commerces");

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        tvfiltrer = view.findViewById(R.id.tvFiltrer);
        tvfiltrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_Filtrer dialog = new Fragment_Filtrer();
                dialog.show(getFragmentManager(), "dialog");
            }
        });

    }


        public void onClickFiltrer(View view) {


    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

}