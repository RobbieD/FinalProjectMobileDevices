package com.example.rob.finalprojectmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class mainScreen extends Activity {
    GamePanel gPanel;
    Character player;
    SavingSystem<Character> savSys;
    mobMaker initMobs;
    String results;
    int rq = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gPanel = new GamePanel(this);
        setContentView(gPanel);
        String name = "";
        results = null;
        savSys = new SavingSystem<Character>(this.getApplicationContext());
        Bundle extras = getIntent().getExtras();
        if(extras !=null){
            name = extras.getString("name");
        }
        player = savSys.mobileDeserial(name, "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  System.out.println(data.getStringExtra("results"));
      //  gPanel.cLog.newLine("does this work", true);
        switch(requestCode) {
            case (1) : {
                if (resultCode == 1) {
                    player = savSys.mobileDeserial(data.getStringExtra("name"),"");
                }
                break;
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(gPanel.sMenu.isSave((int)event.getRawX(),(int)event.getRawY())){
                System.out.println("Save");
                gPanel.cLog.newLine("Saving " + player.getName() + "...", true);
                gPanel.saveChat();
                savSys.mobileSerial(player,player.getName(),"");
            }else if(gPanel.sMenu.isLoad((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Load");
                Intent lgIntent = new Intent(mainScreen.this, LoadGameMenu.class);
                startActivity(lgIntent);
                //player = ;
            }else if(gPanel.sMenu.isHero((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Hero");
                if(player!=null) {
                    gPanel.cLog.newLine("Checking hero statistics.", true);
                    gPanel.saveChat();
                    Intent submitCharIntent = new Intent(mainScreen.this, StatPage.class);
                    submitCharIntent.putExtra("name", player.getName());
                    startActivityForResult(submitCharIntent, rq);
                }else{
                    gPanel.cLog.newLine("Error there is no Character loaded.", true);
                    gPanel.saveChat();
                }
            }else if(gPanel.sMenu.isFight((int) event.getRawX(), (int) event.getRawY())){
                System.out.println("Fight");
                gPanel.cLog.newLine(player.getName() + " joins the fight.", true);
                gPanel.saveChat();
                Intent startFight = new Intent(mainScreen.this, Fight.class);
                startFight.putExtra("name", player.getName());
                startActivityForResult(startFight,1);
            }else if(gPanel.sMenu.isComp((int) event.getRawX(), (int) event.getRawY())) {
                System.out.println("Comp");
                gPanel.cLog.newLine("Comparing heroes.", true);
                gPanel.saveChat();
            }
        }

        return super.onTouchEvent(event);
    }
}
