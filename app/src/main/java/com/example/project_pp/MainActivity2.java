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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    //tittel opvragen om kleur te geven
    TextView textView1;

    //de listvieuw
    ListView listView;

    //dit zijn de namen voor de listview
    String namen[] = {"netflix" , "facebook" , "github","+"};
    int images[] = {R.drawable.rec_1,R.drawable.rec_1,R.drawable.rec_1, R.drawable.rec_1};

    MainActivity2List mainActivity2List;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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

        //listview aan een object vast hangen
        listView = findViewById(R.id.list_view_1);

        //textvieuw kleuren
        setColorToGradiant(textView1);

        //de adapter aan het listview hangen
        mainActivity2List = new MainActivity2List(this,namen,images);
        listView.setAdapter(mainActivity2List);

        //functies
        clickOnListview();
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

    //de functie om te klikken op een listview object
    public void clickOnListview(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("item " + i+ "is gecliked");
                Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}