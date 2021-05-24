package com.example.project_pp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    //tittel opvragen om kleur te geven
    TextView textView1,textView3;

    //de database
    Database database;

    //de editText
    EditText editText1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //dit zorgt ervoor dat deze app word gedraaid in een fullscreen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        //deze gaat de topbar omzetten in een kleur , die doet dit ook met de bot bar
        getWindow().setNavigationBarColor(Color.parseColor("#ffffff"));
        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));


        //texvieuw aan een object vast hangen
        textView1 = findViewById(R.id.text1);
        textView3 = findViewById(R.id.text3);

        //de database verbinden
        database = new Database(this);

        //de edittext verbinden
        editText1 = findViewById(R.id.editText1);

        //textvieuw kleuren
        setColorToGradiant(textView1);

        //functies
        clickOnAdd();
        clickOnHome();
    }

    //de functie van de verf lol
    private void setColorToGradiant(TextView textView){
        TextPaint textPaint = textView.getPaint();
        float width = textPaint.measureText(textView.getText().toString());

        Shader shader = new LinearGradient(width/2,0,width/2,textView.getTextSize(),
                new int[]{
                        Color.parseColor("#00BF8F"),
                        Color.parseColor("#001510")
                },null,Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
    }

    //de functie clickOnAdd
    public void clickOnAdd(){
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!getEditText1().isEmpty()){
                    if (database.uniqueWebsite3(getEditText1())){
                        database.addToTabel3(getEditText1());
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),"deze website bestaal al \n druk op de titel om terug te gaan",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    //de functie click on home
    public void clickOnHome(){
        textView1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View,String>(textView1,"1");
                pairs[1] = new Pair<View,String>(textView1,"2");
                pairs[2] = new Pair<View,String>(textView1,"3");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity4.this,
                        pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }

    //de functie om alles uit de edit te krijgen
    public String getEditText1(){
        String uit = "";
        if (!editText1.getText().toString().isEmpty()){
            uit = editText1.getText().toString().trim();
        }
        return uit;
    }
}