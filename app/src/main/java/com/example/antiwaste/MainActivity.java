package com.example.antiwaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("utilisateur", MODE_PRIVATE);
        long id_utilisateur = prefs.getLong("utilisateur", (long)0);
        Log.e("id utilisateur main: ", String.valueOf(id_utilisateur));
        meowBottomNavigation = findViewById(R.id.navigationBar);

        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_heart));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_map));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_shop));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_person));

        meowBottomNavigation.show(1,true);


        meowBottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES

                switch (model.getId()){
                    case 1 : loadFragment( new FragmentAccueil());break;
                    case 2 :loadFragment( new Fragment_Favoris());break;

                    case 3 : Toast.makeText(MainActivity.this, "pas encore aziz 2", Toast.LENGTH_SHORT).show();break;
                    case 4 :loadFragment( new Fragment_Reservation());break;
                    case 5 :loadFragment( new Fragment_Profile());break;
                }
                return null;
            }
        });

        meowBottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1 : loadFragment( new FragmentAccueil());break;
                }
                // YOUR CODES
                return null;
            }
        });




    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layoutMain,fragment).commit();
    }


}