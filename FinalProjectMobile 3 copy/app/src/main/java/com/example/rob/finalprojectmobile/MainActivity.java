package com.example.rob.finalprojectmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGame(View view){
        Intent ngIntent = new Intent(MainActivity.this,NewGameMenu.class);
        startActivity(ngIntent);
    }

    public void loadGame(View view){
        Intent lgIntent = new Intent(MainActivity.this, LoadGameMenu.class);
        startActivity(lgIntent);

    }

    public void make(View view){
        Intent makeIntent = new Intent(MainActivity.this, mobMaker.class);
        startActivity(makeIntent);
    }

//    public void launchAbout(View view){
//        //Intent aboutIntent = new Intent(MainActivity.this, mainScreen.class);
//        //startActivity(aboutIntent);
//    }

}
