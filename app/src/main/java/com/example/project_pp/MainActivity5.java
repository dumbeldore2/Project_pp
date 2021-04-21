package com.example.project_pp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {

    //tittel opvragen om kleur te geven
    TextView textView1, textView4;

    //de editTexten
    EditText editText1, editText2;

    //intent
    Intent intent;

    //id website
    int id ;

    //database
    Database database;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //dit zorgt ervoor dat deze app word gedraaid in een fullscreen
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        //deze gaat de topbar omzetten in een kleur , die doet dit ook met de bot bar
        getWindow().setNavigationBarColor(Color.parseColor("#ffffff"));
        getWindow().setStatusBarColor(Color.parseColor("#ffffff"));

        //database kopelen
        database = new Database(this);

        //intent koppelen
        intent = getIntent();

        //id koppelen aan het webid
        id = intent.getIntExtra("webId",-1);

        //texvieuw aan een object vast hangen
        textView1 = findViewById(R.id.text1);
        textView4 = findViewById(R.id.text4);

        //de edittext verbinden
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        //textvieuw kleuren
        setColorToGradiant(textView1);

        //functions
        clickOnAdd();
        clickOnHome();
    }


    //de functie van de verf lol
    private void setColorToGradiant(TextView textView) {
        TextPaint textPaint = textView.getPaint();
        float width = textPaint.measureText(textView.getText().toString());

        Shader shader = new LinearGradient(width / 2, 0, width / 2, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#00BF8F"),
                        Color.parseColor("#001510")
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
    }

    //functie clickOnAdd
    public void clickOnAdd() {
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                String[] data = getEditText().clone();
                if (data[0]!= null && !data[0].trim().isEmpty()){
                    if (data[1]!= null && !data[1].trim().isEmpty()){
                        database.addToTabel2(data[0],data[1],id);
                        intent.putExtra("webId", id);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    //de functie click on home
    public void clickOnHome() {
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    //de functie om alles uit de edit te krijgen
    public String[] getEditText() {
        String[] uit = new String[2];
        if (!editText1.getText().toString().isEmpty() && editText1 != null) {
            uit[0] = editText1.getText().toString().trim();
        }
        if (!editText2.getText().toString().isEmpty() && editText2 != null) {
            uit[1] = editText2.getText().toString().trim();
        }
        return uit;
    }
}