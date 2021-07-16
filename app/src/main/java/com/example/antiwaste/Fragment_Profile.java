package com.example.antiwaste;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Profile extends Fragment {

    TextView tvnom;
    TextView tvemail;
    TextView tvtelephone;
    TextView tvmodifierInfoPerso;
    LinearLayout llModifierMotDePasse;
    LinearLayout llDeconnexion;
    public static Utilisateur utilisateur;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public Fragment_Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Profile newInstance() {
        Fragment_Profile fragment = new Fragment_Profile();

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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvnom=view.findViewById(R.id.tvNom);
        tvnom.setText(utilisateur.getPrenom().substring(0,1).toUpperCase()+utilisateur.getPrenom().substring(1).toLowerCase()+" "+utilisateur.getNom().toUpperCase());

        tvemail=view.findViewById(R.id.tvEmail);
        tvemail.setText(utilisateur.getEmail());

        tvtelephone=view.findViewById(R.id.tvTelphone);
        tvtelephone.setText(utilisateur.getNumeroDeTelephone());

        tvmodifierInfoPerso=view.findViewById(R.id.tvModifierInfoPerso);
        tvmodifierInfoPerso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityModifierInfoPerso.class);
                v.getContext().startActivity(intent);
                ActivityModifierInfoPerso.utilisateur=utilisateur;

            }
        });
        llModifierMotDePasse=view.findViewById(R.id.llModifierMotDePasse);
        llModifierMotDePasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ActivityModifierMotDePasse.class);
                v.getContext().startActivity(intent);
                ActivityModifierMotDePasse.utilisateur=utilisateur;
            }
        });
        llDeconnexion=view.findViewById(R.id.llDeconnexion);
        llDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), LoginActivity.class);
                v.getContext().startActivity(intent);
            }
        });


    }


    @Override
    public void onResume(){
        super.onResume();

        //OnResume Fragment
    }

}