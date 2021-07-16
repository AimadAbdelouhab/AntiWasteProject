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
 * Use the {@link Fragment_Reservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Reservation extends Fragment {

    private RecyclerView mRecyclerViewReservations;
    private RecyclerViewAdapterPanierReserves mAdapterReservations;
    private RecyclerView.LayoutManager mLayoutManagerReservations;

    String pdc="http://192.168.43.144:8080";
    String url;

    Long id_utilisateur;


    public Fragment_Reservation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_Reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Reservation newInstance() {
        Fragment_Reservation fragment = new Fragment_Reservation();
        Bundle args = new Bundle();

        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences prefs = getActivity().getSharedPreferences("utilisateur", MODE_PRIVATE);
        id_utilisateur = prefs.getLong("utilisateur", (long)0);
        url=pdc+"/listeDesReservations/"+id_utilisateur;

        listeDesReservations(view,url);

    }
    public void listeDesReservations(View view,String url){

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // Log.e("Liste des commercants:",response.toString());

                        Map<String, ArrayList<Reserver>> retMap = new Gson().fromJson(
                                response.toString(), new TypeToken<HashMap<String, ArrayList<Reserver>>>() {}.getType());


                        Log.e("Liste P P C ",retMap.toString());
                        affichageDesReservations( view, retMap.get("Reservations"));

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error Reser", error.toString());
                        // Toast.makeText(LoginActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }


    public  void affichageDesReservations(View view, ArrayList<Reserver> listeDesReservations) {

        Log.e("Rest Affi Reser", listeDesReservations.toString());

        mLayoutManagerReservations=new LinearLayoutManager(view.getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mLayoutManagerReservations=new GridLayoutManager(view.getContext(), 1,GridLayoutManager.VERTICAL, false);


        mRecyclerViewReservations= (RecyclerView) view.findViewById(R.id.recyclerview_reservation);
        mRecyclerViewReservations.setHasFixedSize(false);
        mRecyclerViewReservations.setLayoutManager(mLayoutManagerReservations);
        mAdapterReservations = new RecyclerViewAdapterPanierReserves(listeDesReservations);
        mRecyclerViewReservations.setAdapter(mAdapterReservations);
        //mAdapterReservations.setDetecteurDeClicSurRecycler(this);


    }


}