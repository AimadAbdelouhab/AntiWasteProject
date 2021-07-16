package com.example.antiwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

public class ActivityModifierMotDePasse extends AppCompatActivity {
    TextInputLayout password;
    TextInputLayout newPassword;
    TextInputLayout confirmNewPassword;
    public static Utilisateur utilisateur;
    String motDepasse,nouveauMotDePasse,confirmerNouveauMotDepasse;

    String pdc="http://192.168.43.144:8080";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_mot_de_passe);

        password= findViewById(R.id.textInputPassword);
        newPassword= findViewById(R.id.textInputNewPassword);
        confirmNewPassword= findViewById(R.id.textInputConfirmPassword);

    }



    public void onClickModifier(View view) {
        motDepasse=password.getEditText().getText().toString();
        nouveauMotDePasse=newPassword.getEditText().getText().toString();
        confirmerNouveauMotDepasse=confirmNewPassword.getEditText().getText().toString();

        if(!motDepasse.equals(utilisateur.getMotDePasse()))
        {
            password.setError("Mot de passe incorrecte");
            password.setErrorEnabled(true);
            password.requestFocus();
            return;
        }else password.setErrorEnabled(false);

        if(nouveauMotDePasse.isEmpty() || nouveauMotDePasse.length()<6)
        {
            newPassword.setError("Saisissez un mot de passe de au moins 6 caractères");
            newPassword.setErrorEnabled(true);
            newPassword.requestFocus();
            return;
        }else newPassword.setErrorEnabled(false);

        if(confirmerNouveauMotDepasse.isEmpty() || !nouveauMotDePasse.equals(confirmerNouveauMotDepasse))
        {
            confirmNewPassword.setError("Les mots de passe ne sont pas identiques");
            confirmNewPassword.setErrorEnabled(true);
            confirmNewPassword.requestFocus();
            return;
        }else confirmNewPassword.setErrorEnabled(false);

        utilisateur.setMotDePasse(nouveauMotDePasse);

        url=pdc+"/modifierMotDePasse/"+utilisateur.getId_utilisateur()+
                "/"+utilisateur.getMotDePasse();

        modifierMotDePasse(url);


    }

    public void modifierMotDePasse( String url){

        RequestQueue requestQueue = Volley.newRequestQueue(ActivityModifierMotDePasse.this);
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

                        Toast.makeText(ActivityModifierMotDePasse.this, "Vos mot de passe à été modifié", Toast.LENGTH_LONG).show();

                        Log.i("Rest Response", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error", error.toString());
                        Toast.makeText(ActivityModifierMotDePasse.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        //int socketTimeout = 30000;//30 seconds - change to what you want
        //RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        //jsonObjectRequest.setRetryPolicy(policy);

        requestQueue.add(jsonObjectRequest);

    }

}