package com.example.antiwaste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextNom,editTextPrenom,editTextEmail,editTextPassword,editTextConfirmePassword,editTextPhoneNumber;
    Button inscription;
    String TAG1="Erreur Response:";
    public static int TIMEOUT_MS=10000;

    String url="http://192.168.43.144:8080/inscriptionUtilisateur";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextNom=findViewById(R.id.editTextNom);
        editTextPrenom=findViewById(R.id.editTextPrenom);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextConfirmePassword=findViewById(R.id.editTextConfirmePassword);
        editTextPhoneNumber=findViewById(R.id.editTextPhoneNumber);
        inscription = findViewById(R.id.registrationButton);
        inscription.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
               String nom =editTextNom.getText().toString().trim();
               String prenom =editTextPrenom.getText().toString().trim();
               String email =editTextEmail.getText().toString().trim();
               String password =editTextPassword.getText().toString().trim();
               String passwordConfirmation =editTextConfirmePassword.getText().toString().trim();
               String telephone =editTextPhoneNumber.getText().toString().trim();


               if(nom.equals(""))
               {
                   editTextNom.setError("Saisissez un nom");
                   editTextNom.requestFocus();
                   return;
               }
               if(prenom.equals(""))
               {
                   editTextPrenom.setError("Saisissez un prénom");
                   editTextPrenom.requestFocus();
                   return;
               }

               if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   editTextEmail.setError("Saisissez une adresse mail valide");
                   editTextEmail.requestFocus();
                   return;
               }

               if(password.isEmpty() || password.length()<6)
               {
                   editTextPassword.setError("Saisissez un mot de passe de au moins 6 caractères");
                   editTextPassword.requestFocus();
                   return;
               }
               if(!password.equals(passwordConfirmation))
               {
                   editTextConfirmePassword.setError("Les mot de passe ne sont pas identiques");
                   editTextConfirmePassword.requestFocus();
                   return;
               }


               if(telephone.equals(""))
               {
                   editTextPhoneNumber.setError("Saisissez un numéro de téléphone");
                   editTextPhoneNumber.requestFocus();
                   return;
               }



               Map<String, String> parameters = new HashMap();
               parameters.put("nom", nom);
               parameters.put("prenom",prenom);
               parameters.put("email", email);
               parameters.put("motDePasse", password);
               parameters.put("numeroDeTelephone", telephone);
               String message ="khra";

               registration(parameters);

           }
        });


        changeStatusBarColor();



    }

    public void onClickConnexion(View view) {
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.stay);
    }

    public void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
              }

    }

    public void registration(Map<String, String>parameters){


        JSONObject jsonParameters = new JSONObject(parameters);

        RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonParameters,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            if (response.getLong("id_utilisateur")==0){
                                Toast.makeText(RegisterActivity.this, "un compte existe déjà avec cette adresse!", Toast.LENGTH_LONG).show();


                            }else{
                                Toast.makeText(RegisterActivity.this, "Bravo! vous venez de vous inscrire", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                overridePendingTransition(R.anim.slide_in_left,R.anim.stay);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("Rest Response", response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Error", error.toString());
                        Toast.makeText(RegisterActivity.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        //int socketTimeout = 30000;//30 seconds - change to what you want
        //RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        //jsonObjectRequest.setRetryPolicy(policy);

        requestQueue.add(jsonObjectRequest);

    }


}