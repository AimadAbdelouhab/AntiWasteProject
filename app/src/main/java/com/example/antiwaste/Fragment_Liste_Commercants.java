package com.example.antiwaste;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Liste_Commercants#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Liste_Commercants extends Fragment {

    private RecyclerView mRecyclerViewCommercantPrimeur,mRecyclerViewCommercantBoucherie,
            mRecyclerViewCommercantBoulangerie,mRecyclerViewCommercantEpicerie;

    private RecyclerViewAdapterCommercant mAdapterCommercantPrimeur,mAdapterCommercantBoucherie,mAdapterCommercantBoulangerie,
            mAdapterCommercantEpicerie;

    private RecyclerView.LayoutManager mLayoutManagerCommercantPrimeur,mLayoutManagerCommercantBoucherie,
            mLayoutManagerCommercantBoulangerie,mLayoutManagerCommercantEpicerie;
    public ListeCommercantsListner listeCommercantsListner;

    String pdc="http://192.168.43.144:8080";
    String url;

    static Map<String, ArrayList<Commercant>> map;
    public static Map<String, ArrayList<Commercant>> lc;


    public static Fragment_Liste_Commercants newInstance() {
        return new Fragment_Liste_Commercants();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_commercants, container, false);



        return view;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        url=pdc+"/listeDesCommercants";
        lc=new HashMap<>();

        map = new HashMap<String, ArrayList<Commercant>>();
        listeDesCommercants(url,view);


        Log.e("lc.toString()",lc.toString());
        lc.toString();

    }


        private ArrayList<Commercant> getDataSource() {
        ArrayList listeCommercant = new ArrayList<Commercant>();
        //ArrayList listePanier= new ArrayList();

        return listeCommercant;
    }

    @Override
    public void onResume() {
        super.onResume();
        url=pdc+":8080/listeDesCommercants";



    }


    public void clicSurRecyclerItem(int position, View v) {
        TextView titre= v.findViewById(R.id.titreCommercant);
        Toast.makeText(getActivity(),"le commercant "+titre.getText().toString(), Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), Activity_Commercant.class));
    }



    public void listeDesCommercants(String url, View view){
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

                        Map<String, ArrayList<Commercant>> retMap = new Gson().fromJson(
                                response.toString(), new TypeToken<HashMap<String, ArrayList<Commercant>>>() {}.getType());

                        map=retMap;
                        Log.e("Liste des  commercants",retMap.toString());
                        affichageCommercants(retMap,view);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Commercant", error.toString());
                       // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);



    }


    public void listeCommercants(Map<String, ArrayList<Commercant>> listeCommercants) {
        lc=listeCommercants;
        Log.e("Rest Error", lc.toString());
    }

    public void affichageCommercants (Map<String, ArrayList<Commercant>> listeCommercants, View view){

        mLayoutManagerCommercantPrimeur=new LinearLayoutManager(getActivity());
        mLayoutManagerCommercantPrimeur=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewCommercantPrimeur = (RecyclerView) view.findViewById(R.id.recyclerview_commercantPrimeur);
        mRecyclerViewCommercantPrimeur.setHasFixedSize(true);
        mRecyclerViewCommercantPrimeur.setLayoutManager(mLayoutManagerCommercantPrimeur);
        mAdapterCommercantPrimeur = new RecyclerViewAdapterCommercant(listeCommercants.get("primeur"));
        mRecyclerViewCommercantPrimeur.setAdapter(mAdapterCommercantPrimeur);



        mLayoutManagerCommercantBoucherie=new LinearLayoutManager(getActivity());
        mLayoutManagerCommercantBoucherie=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewCommercantBoucherie = (RecyclerView) view.findViewById(R.id.recyclerview_commercantBoucherie);
        mRecyclerViewCommercantBoucherie.setHasFixedSize(true);
        mRecyclerViewCommercantBoucherie.setLayoutManager(mLayoutManagerCommercantBoucherie);
        mAdapterCommercantBoucherie = new RecyclerViewAdapterCommercant(listeCommercants.get("boucherie"));
        mRecyclerViewCommercantBoucherie.setAdapter(mAdapterCommercantBoucherie);



        mLayoutManagerCommercantBoulangerie=new LinearLayoutManager(getActivity());
        mLayoutManagerCommercantBoulangerie=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewCommercantBoulangerie = (RecyclerView) view.findViewById(R.id.recyclerview_commercantBoulangerie);
        mRecyclerViewCommercantBoulangerie.setHasFixedSize(true);
        mRecyclerViewCommercantBoulangerie.setLayoutManager(mLayoutManagerCommercantBoulangerie);
        mAdapterCommercantBoulangerie = new RecyclerViewAdapterCommercant(listeCommercants.get("boulangerie"));
        mRecyclerViewCommercantBoulangerie.setAdapter(mAdapterCommercantBoulangerie);



        mLayoutManagerCommercantEpicerie=new LinearLayoutManager(getActivity());
        mLayoutManagerCommercantEpicerie=new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL, false);

        mRecyclerViewCommercantEpicerie = (RecyclerView) view.findViewById(R.id.recyclerview_commercantEpicerie);
        mRecyclerViewCommercantEpicerie.setHasFixedSize(true);
        mRecyclerViewCommercantEpicerie.setLayoutManager(mLayoutManagerCommercantEpicerie);
        mAdapterCommercantEpicerie = new RecyclerViewAdapterCommercant(listeCommercants.get("epicerie"));
        mRecyclerViewCommercantEpicerie.setAdapter(mAdapterCommercantEpicerie);

//        mAdapterCommercantBoucherie.setDetecteurDeClicSurRecycler(this);
//        mAdapterCommercantBoulangerie.setDetecteurDeClicSurRecycler(this);
//        mAdapterCommercantEpicerie.setDetecteurDeClicSurRecycler(this);
//        mAdapterCommercantPrimeur.setDetecteurDeClicSurRecycler(this);



    }
}