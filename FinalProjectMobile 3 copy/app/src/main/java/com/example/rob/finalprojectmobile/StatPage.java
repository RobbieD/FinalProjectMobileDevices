package com.example.rob.finalprojectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;
import java.io.Serializable;

public class StatPage extends AppCompatActivity  {

    //Log.d("inbetween","workd");

    int intp, vitp, wilp, strp, lukp, endp, ptsp;
    String intV, vitV, wilV, strV, lukV, endV, ptsV;
    SavingSystem<Character> savSys;
    String playerName;
    Character playerCharacter = new Character();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        savSys = new SavingSystem<Character>(this.getApplicationContext());

        Bundle extras = getIntent().getExtras();
        Character player;

        if(extras !=null){
            playerName = extras.getString("name");
        }
        playerCharacter = savSys.mobileDeserial(playerName, "");

        updateUI();

        /*
        //all the displaying stats stuff
        intV = Integer.toString(playerCharacter.getINT());
        TextView intTV = (TextView) findViewById(R.id.intP);
        intTV.setText(intV);

        vitV = Integer.toString(playerCharacter.getVIT());
        TextView vitTV = (TextView) findViewById(R.id.vitP);
        vitTV.setText(vitV);

        wilV = Integer.toString(playerCharacter.getWIL());
        TextView wilTV = (TextView) findViewById(R.id.wilP);
        wilTV.setText(wilV);

        strV = Integer.toString(playerCharacter.getSTR());
        TextView strTV = (TextView) findViewById(R.id.strP);
        strTV.setText(strV);

        lukV = Integer.toString(playerCharacter.getLUK());
        TextView lukTV = (TextView) findViewById(R.id.lukP);
        lukTV.setText(lukV);

        endV = Integer.toString(playerCharacter.getEND());
        TextView endTV = (TextView) findViewById(R.id.endP);
        endTV.setText(endV);

        ptsV = Integer.toString(playerCharacter.getPTS());
        TextView ptsTV = (TextView) findViewById(R.id.ptsP);
        ptsTV.setText(ptsV);

        TextView cNameStat = (TextView) findViewById(R.id.txtCharNameStat);
        cNameStat.setText(playerCharacter.getName());

        */





    }

    public void updateUI(){
        intV = Integer.toString(playerCharacter.getINT());
        TextView intTV = (TextView) findViewById(R.id.intP);
        intTV.setText(intV);

        vitV = Integer.toString(playerCharacter.getVIT());
        TextView vitTV = (TextView) findViewById(R.id.vitP);
        vitTV.setText(vitV);

        wilV = Integer.toString(playerCharacter.getWIL());
        TextView wilTV = (TextView) findViewById(R.id.wilP);
        wilTV.setText(wilV);

        strV = Integer.toString(playerCharacter.getSTR());
        TextView strTV = (TextView) findViewById(R.id.strP);
        strTV.setText(strV);

        lukV = Integer.toString(playerCharacter.getLUK());
        TextView lukTV = (TextView) findViewById(R.id.lukP);
        lukTV.setText(lukV);

        endV = Integer.toString(playerCharacter.getEND());
        TextView endTV = (TextView) findViewById(R.id.endP);
        endTV.setText(endV);

        ptsV = Integer.toString(playerCharacter.getPTS());
        TextView ptsTV = (TextView) findViewById(R.id.ptsP);
        ptsTV.setText(ptsV);

        TextView cNameStat = (TextView) findViewById(R.id.txtCharNameStat);
        cNameStat.setText(playerCharacter.getName());

        Button intB = (Button) findViewById(R.id.btnIntP);
        Button vitB = (Button) findViewById(R.id.btnVitP);
        Button wilB = (Button) findViewById(R.id.btnWilP);
        Button strB = (Button) findViewById(R.id.btnStrP);
        Button lukB = (Button) findViewById(R.id.btnLukP);
        Button endB = (Button) findViewById(R.id.btnEndP);

        if(playerCharacter.getPTS()<1){
            intB.setVisibility(View.INVISIBLE);
            vitB.setVisibility(View.INVISIBLE);
            wilB.setVisibility(View.INVISIBLE);
            strB.setVisibility(View.INVISIBLE);
            lukB.setVisibility(View.INVISIBLE);
            endB.setVisibility(View.INVISIBLE);
        }
    }


    public void intP(View view){
        //if pts > 0
        //find pts in character -1
        //find int in character +1
        //update ui
        int intel = playerCharacter.getINT();
        int pts = playerCharacter.getPTS();
        if (pts>0){
            pts--;
            playerCharacter.setPTS(pts);
            intel++;
            playerCharacter.setINT(intel);

            updateUI();
        }
    }

    public void vitP(View view){
        int vit = playerCharacter.getVIT();
        int pts = playerCharacter.getPTS();
        if (pts>0) {
            pts--;
            playerCharacter.setPTS(pts);
            vit++;
            playerCharacter.setVIT(vit);

            updateUI();
        }
    }

    public void wilP(View view){
        int wil = playerCharacter.getWIL();
        int pts = playerCharacter.getPTS();
        if (pts>0){
            pts--;
            playerCharacter.setPTS(pts);
            wil++;
            playerCharacter.setWIL(wil);

            updateUI();
        }
}

    public void strP(View view){
        int str = playerCharacter.getSTR();
        int pts = playerCharacter.getPTS();
        if (pts>0) {
            pts--;
            playerCharacter.setPTS(pts);
            str++;
            playerCharacter.setSTR(str);

            updateUI();
        }
    }

    public void lukP(View view) {
        int luk = playerCharacter.getLUK();
        int pts = playerCharacter.getPTS();
        if (pts > 0) {
            pts--;
            playerCharacter.setPTS(pts);
            luk++;
            playerCharacter.setLUK(luk);

            updateUI();
        }
    }

    public void endP(View view){
        int end = playerCharacter.getEND();
        int pts = playerCharacter.getPTS();
        if (pts>0) {
            pts--;
            playerCharacter.setPTS(pts);
            end++;
            playerCharacter.setEND(end);

            updateUI();
        }
    }

    public void start(View view){
        savSys.mobileSerial(playerCharacter, playerCharacter.getName(),"");
        finish();
        /*Intent startTheGameIntent = new Intent(StatPage.this, mainScreen.class);
        startTheGameIntent.putExtra("name", playerCharacter.getName());
        startActivity(startTheGameIntent);*/
    }




}
