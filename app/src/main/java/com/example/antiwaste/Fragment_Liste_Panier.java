package com.example.antiwaste;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Liste_Panier#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Liste_Panier extends Fragment implements DetecteurDeClicSurRecycler{

    private RecyclerView mRecyclerViewPanierDuJour,mRecyclerViewPanierVegetaux,mRecyclerViewPanierBoucherie,
            mRecyclerViewPanierBoulangerie,mRecyclerViewPanierEpicerie;
    private RecyclerViewAdapterPanier mAdapterPanierDuJour,mAdapterPanierVegetaux,mAdapterPanierBoucherie,mAdapterPanierBoulangerie,
            mAdapterPanierEpicerie;
    private RecyclerView.LayoutManager mLayoutManagerPanierDuJour,mLayoutManagerPanierVegetaux,mLayoutManagerPanierBoucherie,
            mLayoutManagerPanierBoulangerie,mLayoutManagerPanierEpicerie;

    String pdc="http://192.168.43.144:8080";
    String url,url2;
    ArrayList<Bitmap> imagesPaniers;
    ImageView mImageView;




    public static Fragment_Liste_Panier newInstance() {
        return new Fragment_Liste_Panier();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_panier, container, false);


        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        url=pdc+"/listeDesPaniers";
        url2=pdc+"/listeDesPaniersParHeure";
        listeDesPaniersASauver(url2,view);
        listeDesPaniers(url,view);

    }


        @Override
    public void onResume() {
        super.onResume();


    }


    public void clicSurRecyclerItem(int position, View v) {

       // TextView titre= v.findViewById(R.id.titrePanier);
               startActivity(new Intent(getActivity(), Activity_Panier.class));
    }


    public void listeDesPaniers(String url, View view){
        Log.e("Liste des  maps",url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("Liste des commercants:",response.toString());

                        Map<String, ArrayList<Panier>> retMap = new Gson().fromJson(
                                response.toString(), new TypeToken<HashMap<String, ArrayList<Panier>>>() {}.getType());
                        //Toast.makeText(getActivity(), retMap.get("primeur").toString(),Toast.LENGTH_SHORT).show();


                        Log.e("Liste des  paniers",retMap.toString());
                        affichagePaniers(retMap,view);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Panier", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }


    public void listeDesPaniersASauver(String url, View view){
        Log.e("Liste des  maps",url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("Liste des commercants:",response.toString());

                        Map<String, ArrayList<Panier>> retMap = new Gson().fromJson(
                                response.toString(), new TypeToken<HashMap<String, ArrayList<Panier>>>() {}.getType());

                        Log.e("Liste des  paniers",retMap.toString());
                        affichagePaniersASauver(retMap,view);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Panier", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);



    }

    public void affichagePaniers(Map<String, ArrayList<Panier>> listePaniers, View view){


        mLayoutManagerPanierVegetaux=new LinearLayoutManager(getActivity());
        mLayoutManagerPanierVegetaux=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewPanierVegetaux = (RecyclerView) view.findViewById(R.id.recyclerview_panierVegetaux);
        mRecyclerViewPanierVegetaux.setHasFixedSize(true);
        mRecyclerViewPanierVegetaux.setLayoutManager(mLayoutManagerPanierVegetaux);
        mAdapterPanierVegetaux = new RecyclerViewAdapterPanier(listePaniers.get("primeur"));
        mRecyclerViewPanierVegetaux.setAdapter(mAdapterPanierVegetaux);


        mLayoutManagerPanierBoucherie=new LinearLayoutManager(getActivity());
        mLayoutManagerPanierBoucherie=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewPanierBoucherie = (RecyclerView) view.findViewById(R.id.recyclerview_panierBoucherie);
        mRecyclerViewPanierBoucherie.setHasFixedSize(true);
        mRecyclerViewPanierBoucherie.setLayoutManager(mLayoutManagerPanierBoucherie);
        mAdapterPanierBoucherie = new RecyclerViewAdapterPanier(listePaniers.get("boucherie"));
        mRecyclerViewPanierBoucherie.setAdapter(mAdapterPanierBoucherie);


        mLayoutManagerPanierBoulangerie=new LinearLayoutManager(getActivity());
        mLayoutManagerPanierBoulangerie=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewPanierBoulangerie = (RecyclerView) view.findViewById(R.id.recyclerview_panierBoulangerie);
        mRecyclerViewPanierBoulangerie.setHasFixedSize(true);
        mRecyclerViewPanierBoulangerie.setLayoutManager(mLayoutManagerPanierBoulangerie);
        mAdapterPanierBoulangerie = new RecyclerViewAdapterPanier(listePaniers.get("boulangerie"));
        mRecyclerViewPanierBoulangerie.setAdapter(mAdapterPanierBoulangerie);


        mLayoutManagerPanierEpicerie=new LinearLayoutManager(getActivity());
        mLayoutManagerPanierEpicerie=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewPanierEpicerie = (RecyclerView) view.findViewById(R.id.recyclerview_panierEpicerie);
        mRecyclerViewPanierEpicerie.setHasFixedSize(true);
        mRecyclerViewPanierEpicerie.setLayoutManager(mLayoutManagerPanierEpicerie);
        mAdapterPanierEpicerie = new RecyclerViewAdapterPanier(listePaniers.get("epicerie"));
        mRecyclerViewPanierEpicerie.setAdapter(mAdapterPanierEpicerie);





        mAdapterPanierVegetaux.setDetecteurDeClicSurRecycler(this);
        mAdapterPanierBoucherie.setDetecteurDeClicSurRecycler(this);
        mAdapterPanierEpicerie.setDetecteurDeClicSurRecycler(this);
        mAdapterPanierBoulangerie.setDetecteurDeClicSurRecycler(this);


    }


    public void affichagePaniersASauver(Map<String, ArrayList<Panier>> listePaniersASauver, View view){

        mLayoutManagerPanierDuJour=new LinearLayoutManager(getActivity());
        mLayoutManagerPanierDuJour=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);


        mRecyclerViewPanierDuJour = (RecyclerView) view.findViewById(R.id.recyclerview_panierDuJour);
        mRecyclerViewPanierDuJour.setHasFixedSize(true);
        mRecyclerViewPanierDuJour.setLayoutManager(mLayoutManagerPanierDuJour);
        mAdapterPanierDuJour = new RecyclerViewAdapterPanier(listePaniersASauver.get("aSauver"));
        mRecyclerViewPanierDuJour.setAdapter(mAdapterPanierDuJour);



        mAdapterPanierDuJour.setDetecteurDeClicSurRecycler(this);



    }


}