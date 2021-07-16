package com.example.antiwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityModifierInfoPerso extends AppCompatActivity {

    TextInputLayout nom;
    TextInputLayout prenom;
    TextInputLayout email;
    TextInputLayout numeroDeTelephone;
    static Utilisateur utilisateur;
    String pdc="http://192.168.43.144:8080";
    String url;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_info_perso);

        nom=findViewById(R.id.textInputNom);
        nom.getEditText().setText(Fragment_Profile.utilisateur.getNom());

        prenom=findViewById(R.id.textInputPrenom);
        prenom.getEditText().setText(utilisateur.getPrenom());

        email=findViewById(R.id.textInputEmail);
        email.getEditText().setText(utilisateur.getEmail());

        numeroDeTelephone=findViewById(R.id.textInputPhoneNumber);
        numeroDeTelephone.getEditText().setText(utilisateur.getNumeroDeTelephone());


    }

    public void onClickModifier(View view) {
        String noms=nom.getEditText().getText().toString();
        String prenoms=prenom.getEditText().getText().toString();
        String emails=email.getEditText().getText().toString();
        String telephones=numeroDeTelephone.getEditText().getText().toString();

        utilisateur.setNom(noms);
        utilisateur.setPrenom(prenoms);
        utilisateur.setEmail(emails);
        utilisateur.setNumeroDeTelephone(telephones);




        url=pdc+"/modifierUtilisateur/"+utilisateur.getId_utilisateur()+
                "/"+utilisateur.getNom()+
                "/"+utilisateur.getPrenom()+
                "/"+utilisateur.getEmail()+
                "/"+utilisateur.getNumeroDeTelephone();

        modifierProfile(url);

    }

    public void modifierProfile( String url){

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityModifierInfoPerso.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Utilisateur utilisateurjson = new Gson().fromJson(
                                response.toString(), new TypeToken<Utilisateur>() {}.getType());
                        utilisateur=utilisateurjson;
                        Fragment_Profile.utilisateur=utilisateurjson;

                        Toast.makeText(ActivityModifierInfoPerso.this, "Vos informations ont été modifiés", Toast.LENGTH_LONG).show();

                        Log.i("Rest Response", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error", error.toString());
                        Toast.makeText(ActivityModifierInfoPerso.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        //int socketTimeout = 30000;//30 seconds - change to what you want
        //RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        //jsonObjectRequest.setRetryPolicy(policy);

        requestQueue.add(jsonObjectRequest);

    }

}