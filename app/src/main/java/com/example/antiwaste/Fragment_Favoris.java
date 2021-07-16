package com.example.antiwaste;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Favoris#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Favoris extends Fragment implements  DetecteurDeClicSurRecycler{

    private RecyclerView mRecyclerViewCommercantFavoris;
    private RecyclerViewAdapterCommercant mAdapterCommercantFavoris;
    private RecyclerView.LayoutManager mLayoutManagerCommercantFavoris;

    String pdc="http://192.168.43.144:8080";
    String url;

    Long id_utilisateur;


    public Fragment_Favoris() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_Favoris.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Favoris newInstance() {
        Fragment_Favoris fragment = new Fragment_Favoris();

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
        return inflater.inflate(R.layout.fragment_favoris, container, false);
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = getActivity().getSharedPreferences("utilisateur", MODE_PRIVATE);
        id_utilisateur = prefs.getLong("utilisateur", (long)0);
        url=pdc+"/listeDesCommercantsFavoris/"+id_utilisateur;

        listeDesCommercantsFavoris(view,url);

    }


        public void listeDesCommercantsFavoris(View view,String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("Liste des commercants:",response.toString());

                        Map<String, ArrayList<Commercant>> retMap = new Gson().fromJson(
                                response.toString(), new TypeToken<HashMap<String, ArrayList<Commercant>>>() {}.getType());


                        Log.e("Liste P P C ",retMap.toString());
                        affichageDesCommercantsFavoris( view, retMap.get("Commercant"));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Favoris", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }


    public void affichageDesCommercantsFavoris(View view, ArrayList<Commercant> listeDesCommercantsFavoris) {

        Log.e("Rest Affi Commercant", listeDesCommercantsFavoris.toString());

        mLayoutManagerCommercantFavoris=new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManagerCommercantFavoris=new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL, false);


        mRecyclerViewCommercantFavoris= (RecyclerView) view.findViewById(R.id.recyclerview_favoris);
        mRecyclerViewCommercantFavoris.setHasFixedSize(false);
        mRecyclerViewCommercantFavoris.setLayoutManager(mLayoutManagerCommercantFavoris);
        mAdapterCommercantFavoris = new RecyclerViewAdapterCommercant(listeDesCommercantsFavoris);
        mRecyclerViewCommercantFavoris.setAdapter(mAdapterCommercantFavoris);
        mAdapterCommercantFavoris.setDetecteurDeClicSurRecycler(this);





    }

    @Override
    public void clicSurRecyclerItem(int position, View v) {

    }

    @Override
    public void onResume() {
        super.onResume();


    }
}