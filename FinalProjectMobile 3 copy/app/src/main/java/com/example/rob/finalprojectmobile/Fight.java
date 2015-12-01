package com.example.rob.finalprojectmobile;

import android.app.admin.SystemUpdatePolicy;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Fight extends AppCompatActivity {

    errorLog bugLog = new errorLog();
    SavingSystem<CharacterDBHelper> savDB;
    CharacterDBHelper CDB;
    public int lv, intl, wil, vit, str, luk, end;

    public TextView gameFeed;

    public  String results ="";

    //AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);

    public SoundPool sounds;

    //sounds.setOnLoadCompleteListener()

    //final MediaPlayer runaway = MediaPlayer.create(this, R.raw.runaway);
    //final MediaPlayer stats = MediaPlayer.create(this, R.raw.pageturn);

    Random randle = new Random();
    TextView battleStatsP;
    TextView battleStatsE;

    SavingSystem<Character> savSys;
    SavingSystem<Character> loadEnemyList = new SavingSystem<Character>("enemyDB.txt");
    Character playerCharacter = new Character();
    String playerName;

    int aSoundID;
    int sSoundID;
    int rSoundID;

    Enemy enemy = new Enemy();
    String[] enemyType;
    DamageSystem ds;

    public void randomStats(){
        lv = playerCharacter.getLVL();
        intl = randle.nextInt(20)+ lv*5;
        wil = randle.nextInt(20)+ lv*5;
        vit = randle.nextInt(20)+ lv*5;
        str = randle.nextInt(20)+ lv*5;
        luk = randle.nextInt(20)+ lv*5;
        end = randle.nextInt(20)+ lv*5;

    }
    /*    //making a shitty database
    public void createEnemy() {
        randomStats();
        Enemy enemy1 = enemyDB.createEnemy(lv, intl, wil, vit, str, luk, end);

        randomStats();
        Enemy enemy2 = enemyDB.createEnemy(lv, intl, wil, vit, str, luk, end);

        randomStats();
        Enemy enemy3 = enemyDB.createEnemy(lv, intl, wil, vit, str, luk, end);

        randomStats();
        Enemy enemy4 = enemyDB.createEnemy(lv, intl, wil, vit, str, luk, end);

        randomStats();
        Enemy enemy5 = enemyDB.createEnemy(lv, intl, wil, vit, str, luk, end);

    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        savSys = new SavingSystem<Character>(this.getApplicationContext());

        Bundle extras = getIntent().getExtras();

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {

            sounds = new SoundPool.Builder().setMaxStreams(5).build();//(5, AudioManager.STREAM_MUSIC, 0);

            aSoundID = sounds.load(this, R.raw.sword, 1);
            sSoundID = sounds.load(this, R.raw.pageturn, 1);
            rSoundID = sounds.load(this, R.raw.runaway, 1);

        }else{
            sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 1);

            aSoundID = sounds.load(this, R.raw.sword, 1);
            sSoundID = sounds.load(this, R.raw.pageturn, 1);
            rSoundID = sounds.load(this, R.raw.runaway, 1);
        }

        if(extras !=null){
            playerName = extras.getString("name");
        }
        playerCharacter = savSys.mobileDeserial(playerName, "");


        //System.out.println("Fun Bags");
        //System.out.println(this.getApplicationContext().getFilesDir());
        //enemyType = loadEnemyList.mobileExtractRaw();
        ArrayList<Enemy> enemyList = new mobMaker().defaultMobs(this);
        enemy = enemyList.get(new Random().nextInt(enemyList.size()));
        System.out.println("son of a ");
        ds = new DamageSystem(playerCharacter, enemy);
        System.out.println("this is why we cant have nice things");
        //ds.player = playerCharacter;
        //ds.enemy = enemy;
        battleStatsP = (TextView) findViewById(R.id.battleStatsP);
        battleStatsE = (TextView) findViewById(R.id.battleStatsE);

        playerCharacter.mobileCompareStats();
        enemy.mobileCompareStats();
        battleStatsP.setText(playerCharacter.compare);
        battleStatsE.setText(enemy.compare);

        System.out.println("kill me please ");

    }

    public void fight(View view){
        if(ds.isAlive(ds.enemy)) {
            ds.setChoice(1);

            sounds.play(aSoundID, 1f, 1f, 0, 0, 1f);
            //attack.playSoundEffect(R.raw.sword);

            ds.mobileBattle();

            gameFeed = (TextView) findViewById(R.id.fightFeed);
            gameFeed.setMovementMethod(new ScrollingMovementMethod());
            gameFeed.append(ds.bLog + "\n");
            if (ds.bLog.contains("You")){

                results +="You have perished!";
                Intent lose = new Intent(Fight.this, mainScreen.class);
                lose.putExtra("result", results);
                startActivity(lose);

            }

            playerCharacter.mobileCompareStats();
            enemy.mobileCompareStats();
            battleStatsP.setText(playerCharacter.compare);
            battleStatsE.setText(enemy.compare);

            ds.turn++;
            ds.setChoice(0);
        }else {
            gameFeed.append("No Enemy to Fight\n");
        }
    }

    public void bag(View view){
        //open bag to do something

        //if(!ds.isAlive(ds.enemy)) {
            sounds.play(sSoundID, 1f,1f,0,0,1f);
            savSys.mobileSerial(playerCharacter, playerName, "");
            Intent backToStats = new Intent(Fight.this, StatPage.class);
            backToStats.putExtra("name",playerName);
            startActivity(backToStats);
            finish();
        //}


    }

    public void run(View view){
        // ds.choice = 5;

        if(ds.isAlive(ds.enemy)){
            results="You Ran like a Coward";
        }else{
            results+="You were Victorious and gained "+ ds.enemy.getCHARNUM() + " EXP Points!";
        }

        //System.out.println(playerCharacter);

        //System.out.println(ds.player);

        playerCharacter = ds.player;

        sounds.play(rSoundID, 1f,1f,0,0,1f);

        //System.out.println(playerCharacter);

        savSys.mobileSerial(playerCharacter, playerCharacter.getName(), "");
        Intent battleEnd = new Intent();
        battleEnd.putExtra("results", results);
        battleEnd.putExtra("name", playerCharacter.getName());
        setResult(1,battleEnd);
        finish();
    }

}
