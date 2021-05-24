package com.example.project_pp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    //tittel opvragen om kleur te geven
    TextView textView1,textView2,textView1dialog1 ,textView2dialog1 ;

    //de listvieuw
    ListView listView;

    //de database
    Database database;

    //dit zijn de namen voor de listview
    String namen[] = {};
    //ArrayList<String> namen = new ArrayList<>();
    int images[] = {};
    int ids[] = {};

    //de listview adapter
    MainActivity2List mainActivity2List;

    //Dialog
    Dialog dialog;

    //imageview
    ImageView imageView1dialog1 ;

    //editText dialog
    EditText editTextdialog1;

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
        textView2 = findViewById(R.id.text2);

        //listview aan een object vast hangen
        listView = findViewById(R.id.list_view_1);

        //de database linken
        database = new Database(this);

        //textvieuw kleuren
        setColorToGradiant(textView1);

        //om de arraylijsten aan te passen voor de listvieuw
        comboMaker();

        //de adapter aan het listview hangen
        mainActivity2List = new MainActivity2List(this,namen,images);
        listView.setAdapter(mainActivity2List);

        //dialogt koppelen
        dialog = new Dialog(this);

        //functies
        clickOnListview();
        clickOnAdd();
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
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),MainActivity3.class);
                intent.putExtra("webId", ids[i]);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(listView,"1");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity2.this,
                        pairs);

                startActivity(intent,options.toBundle());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                dialog.setContentView(R.layout.dialoge1);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                imageView1dialog1 = dialog.findViewById(R.id.imageViewPopup);
                textView1dialog1 = dialog.findViewById(R.id.textView_edit);
                textView2dialog1 = dialog.findViewById(R.id.textView_verwijderen);
                editTextdialog1 = dialog.findViewById(R.id.editTextDialog);
                editTextdialog1.setText(database.getTable_1_col_2(ids[i]));

                //button om de popup te closen
                imageView1dialog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                //button voor een edit
                textView1dialog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        database.rename(ids[i],editTextdialog1.getText().toString());
                        startActivity(intent);
                    }
                });

                //button voor de verwijdering
                textView2dialog1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                        database.deleteTable1Row(ids[i]);
                        startActivity(intent);
                    }
                });
                dialog.show();
            return true;
            }
        });
    }

    //fun click on add
    public void clickOnAdd(){
        textView2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity4.class);

               Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(textView2,"1");
                pairs[1] = new Pair<View,String>(textView2,"3");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity2.this,
                        pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }

    //fun make combo voor listview
    public void comboMaker(){
        ArrayList<String> uit = database.namen();
        int[] uit2 = new int[uit.size()];
        namen = new String[uit.size()];

        for (int i = 0; i < uit.size() ; i++){
            uit2[i] = R.drawable.rec_1;
            namen[i] = uit.get(i);
        }

        images = uit2;
        ids = database.idsTableOne().clone();

    }
}