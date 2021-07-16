package com.example.antiwaste;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityCommercant extends AppCompatActivity {

    EditText editTextEmail,editTextPassword;
    String eduspot="http://172.24.6.104";
    String pdc="http://192.168.43.144";
    String url=pdc+":8080/connexionUtilisateur/";
    String urlUC=pdc+":8080/getUtilisateur/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_commercant);

        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if(ContextCompat.checkSelfPermission(LoginActivityCommercant.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            finish();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("utilisateur", MODE_PRIVATE);
        long id_utilisateur = prefs.getLong("utilisateur", (long)0);
        if (id_utilisateur!=0){
            urlUC=urlUC+id_utilisateur;
           // getUtilisateur(urlUC);
            //startActivity(new Intent(LoginActivityCommercant.this,MainActivity.class));

        }

    }

    public void onClickRegistration(View view) {
        startActivity(new Intent(this,RegisterActivityCommercant.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void onClickConnexion(View view) {

        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

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

        Map<String,String>parameters = new HashMap<>();
        parameters.put("email",email);
        parameters.put("motDePasse",password);



        url=url+email+"/"+password;
        //startActivity(new Intent(LoginActivity.this,MainActivity.class));
        connexion(parameters);
        url=pdc+":8080/connexionUtilisateur/";




//        Dexter.withContext(LoginActivity.this)
//        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//        .withListener(new PermissionListener() {
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                startActivity(new Intent(LoginActivity.this,MapsActivity.class));
//                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
//
//            }
//
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                if(permissionDeniedResponse.isPermanentlyDenied()){
//                    AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this);
//                    builder.setTitle("Permission Denied")
//                    .setMessage("L'acces a la localisation du téléphone à été refusé. Modifiez le dans les parametres")
//                    .setNegativeButton("Annuler", null)
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent();
//                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                            intent.setData(Uri.fromParts("package", getPackageName(), null));
//                        }
//                    }).show();
//                }else {
//                    Toast.makeText(LoginActivity.this,"Permission refusé", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                permissionToken.continuePermissionRequest();
//            }
//        }).check();



    }



    public void connexion(Map<String, String> parameters){


        JSONObject jsonParameters = new JSONObject(parameters);


        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivityCommercant.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getLong("id_utilisateur")==0){
                                Toast.makeText(LoginActivityCommercant.this, "E-mail ou mot de passe incorrecte!", Toast.LENGTH_LONG).show();

                            }else { Toast.makeText(LoginActivityCommercant.this, "Bienvenue!", Toast.LENGTH_LONG).show();

                                Utilisateur utilisateur = new Gson().fromJson(
                                        response.toString(), new TypeToken<Utilisateur>() {}.getType());
                                Fragment_Profile.utilisateur=utilisateur;
                                Log.e("Rest Response", String.valueOf(utilisateur));

                                SharedPreferences sharedPreferences = getSharedPreferences("utilisateur", MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putLong("utilisateur", response.getLong("id_utilisateur"));
                                editor.apply();
                                startActivity(new Intent(LoginActivityCommercant.this,MainActivity.class));
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
                        Toast.makeText(LoginActivityCommercant.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

       requestQueue.add(jsonObjectRequest);

    }

    public void getUtilisateur(String url){



        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivityCommercant.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response.getLong("id_utilisateur")==0){
                                Toast.makeText(LoginActivityCommercant.this, "E-mail ou mot de passe incorrecte!", Toast.LENGTH_LONG).show();

                            }else {
                                Utilisateur utilisateur = new Gson().fromJson(
                                        response.toString(), new TypeToken<Utilisateur>() {}.getType());
                                Fragment_Profile.utilisateur=utilisateur;
                                Log.e("Rest Response", String.valueOf(utilisateur));

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
                        Toast.makeText(LoginActivityCommercant.this, "Some error occurred -> "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }

    public void onClickEspaceClient(View view) {
        startActivity(new Intent(LoginActivityCommercant.this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }
}