package com.example.project_pp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {

    //textview voor button
    TextView textView3, textView5;

    //ints
    int [] ints;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        //dit zorgt ervoor dat deze app word gedraaid in een fullscreen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        //deze gaat de topbar omzetten in een kleur , die doet dit ook met de bot bar
        getWindow().setNavigationBarColor(Color.parseColor("#001510"));
        getWindow().setStatusBarColor(Color.parseColor("#00BF8F"));

        //textviews koppelen
        textView3 = findViewById(R.id.text3);
        textView5 = findViewById(R.id.text5);

        //ints lengte
        ints = new int[2];

        //fun
        click1();
        click2();
    }

    public void click1(){
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ints[0]= 1;
                comboChecker();
            }
        });
    }
    public void click2(){
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ints[1]= 1;
                comboChecker();
            }
        });
    }

    public void comboChecker(){
        if (ints[0] == 1 && ints[1] == 1){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}