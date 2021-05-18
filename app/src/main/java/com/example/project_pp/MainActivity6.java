package com.example.project_pp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.bson.Document;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class MainActivity6 extends AppCompatActivity {

    //textview voor button
    TextView textView3, textView5;

    //ints
    int [] ints;

    //mongodb constanten
    String appId = "project-pp-yfznu";
    private App app;
    MongoClient mongoClient;
    MongoDatabase mongoDatabase;
    Long id;
    User user;
    MongoCollection<Document> mongoCollection;

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

        //realm init
        Realm.init(this);
        app = new App(new AppConfiguration.Builder(appId).build());
        Credentials credentials = Credentials.emailPassword("yago.engels@gmail.com", "Judolessen12");
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if (result.isSuccess()) {

                    Log.v("User", "tis gelukt lololl");
                } else {

                    Log.v("User", "tis ni echt gelukt");

                }
            }
        });
    }

    public void click1(){
        textView3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ints[0]= 1;
                comboChecker();
            }
        });
    }
    public void click2(){
        textView5.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ints[1]= 1;
                comboChecker();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void comboChecker(){
        if (ints[0] == 1 && ints[1] == 1){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);

            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View,String>(textView3,"1");
            pairs[1] = new Pair<View,String>(textView5,"2");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity6.this,
                    pairs);
            startActivity(intent,options.toBundle());
        }
    }
}