package com.example.rob.finalprojectmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class mobMaker extends Activity {
        private ArrayList<Enemy> characters = null;
        private int characterIndex = -1;
        SavingSystem<CharacterDBHelper> savDB = new SavingSystem<CharacterDBHelper>();
        CharacterDBHelper CdbHelper = new CharacterDBHelper(this);

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_mob_maker);
            Button nextBtn = (Button) findViewById(R.id.btnNextContact);
            Button prevBtn = (Button) findViewById(R.id.btnPrevContact);
            nextBtn.setEnabled(false);
            prevBtn.setEnabled(false);
            // delete any contacts from a previous execution
            CdbHelper.deleteAllCharacters();

            CdbHelper.createCharacter("", "GOBLIN", "CHIEFTAIN", 0, 0, 15, 10, 10, 5, 10);
            CdbHelper.createCharacter("", "ZOMBIE", "BIG BOY", 0, 0, 5, 10, 10, 20, 5);
            CdbHelper.createCharacter("", "ZOMBIE", "BODY BUILDER", 0, 10, 20, 10, 20, 10, 10);

            // create a bunch of contacts (testing create)
            // add them to the local list (testing read all)
            this.updateList();
            this.characterIndex = 0;
            displayCharacter(this.characters.get(characterIndex));
        }

        public ArrayList<Enemy> defaultMobs(Context c){
            CharacterDBHelper CdbHelper = new CharacterDBHelper(c);
            CdbHelper.createCharacter("", "GOBLIN", "CHIEFTAIN", 0, 0, 15, 10, 10, 5, 10);
            CdbHelper.createCharacter("", "ZOMBIE", "BIG BOY", 0, 0, 5, 10, 10, 20, 5);
            CdbHelper.createCharacter("", "ZOMBIE", "BODY BUILDER", 0, 10, 20, 10, 20, 10, 10);
            return CdbHelper.getAllCharacters();
        }

        public void updateList(){
            this.characters = CdbHelper.getAllCharacters();
            for(int i = 0; i < this.characters.size(); i++){
                Log.d("debug", (i + 1) + ". " + characters.get(i).getName());
            }
        }
        public void nextCharacter(View v) {
            nextCharacter();
        }

        private void nextCharacter() {
            this.characterIndex++;
            Button nextBtn = (Button) findViewById(R.id.btnNextContact);
            Button prevBtn = (Button) findViewById(R.id.btnPrevContact);
            if (this.characterIndex >= this.characters.size()-1) {
                nextBtn.setEnabled(false);
                prevBtn.setEnabled(true);
                //this.productIndex--;
            }else{
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
            }

            displayCharacter(this.characters.get(this.characterIndex));
        }

        public void prevCharacter(View v) {
            prevCharacter();
        }

        private void prevCharacter() {
            this.characterIndex--;
            Button prevBtn = (Button) findViewById(R.id.btnPrevContact);
            Button nextBtn = (Button) findViewById(R.id.btnNextContact);
            if (this.characterIndex < 1) {
                prevBtn.setEnabled(false);
                nextBtn.setEnabled(true);
                //this.productIndex++;
            }else{
                prevBtn.setEnabled(true);
                nextBtn.setEnabled(true);
            }

            displayCharacter(this.characters.get(this.characterIndex));
        }

        private void displayCharacter(Character c) {
            TextView nameField = (TextView)findViewById(R.id.lblNameValue);
            TextView raceField = (TextView)findViewById(R.id.lblRaceValue);
            TextView classField = (TextView)findViewById(R.id.lblClassValue);
            TextView levelField = (TextView)findViewById(R.id.lblLevelValue);
            TextView intField = (TextView)findViewById(R.id.lblIntValue);
            TextView vitField = (TextView)findViewById(R.id.lblVitValue);
            TextView wilField = (TextView)findViewById(R.id.lblWilValue);
            TextView strField = (TextView)findViewById(R.id.lblStrValue);
            TextView lukField = (TextView)findViewById(R.id.lblLukValue);
            TextView endField = (TextView)findViewById(R.id.lblEndValue);

            nameField.setText(c.getName());
            raceField.setText(c.getRace());
            classField.setText(c.getClassID());
            levelField.setText(c.getLVL()+"");
            intField.setText(c.getINT()+"");
            vitField.setText(c.getVIT()+"");
            wilField.setText(c.getWIL()+"");
            strField.setText(c.getSTR()+"");
            lukField.setText(c.getLUK()+"");
            endField.setText(c.getEND()+"");

            //this.getOrigin(product.getPrice());
        }

        public void addCharactertoDB(View view){
            Intent intent = new Intent(mobMaker.this, AddCharacterActivity.class);
            startActivityForResult(intent, 3);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            Enemy newCharacter = null;
            super.onActivityResult(requestCode, resultCode, data);
            Button prevBtn = (Button) findViewById(R.id.btnPrevContact);
            Button nextBtn = (Button) findViewById(R.id.btnNextContact);
            switch(requestCode) {
                case(3):{
                    if (resultCode == 1) {
                        //Log.d("debug", data.getStringExtra("newProdName") + " " + data.getStringExtra("newProdDescript") + " " + data.getDoubleExtra("newProdPrice", 0.0));
                        newCharacter = CdbHelper.createCharacter(data.getStringExtra("newCharName"),
                                data.getStringExtra("newCharRace"),
                                data.getStringExtra("newCharClass"),
                                data.getIntExtra("newCharLevel", 0),
                                data.getIntExtra("newCharIntellect", 0),
                                data.getIntExtra("newCharVitality", 0),
                                data.getIntExtra("newCharWillpower", 0),
                                data.getIntExtra("newCharStrength", 0),
                                data.getIntExtra("newCharLuck", 0),
                                data.getIntExtra("newCharEndurance", 0)
                        );
                        Log.d("DEBUG", newCharacter.getName()+"-----------------------------------------------------");
                        this.updateList();
                        if(characters.size() == 1){
                            prevBtn.setEnabled(false);
                            nextBtn.setEnabled(false);
                        }else if (this.characterIndex == 0) {
                            prevBtn.setEnabled(false);
                            nextBtn.setEnabled(true);
                        }else if (this.characterIndex == this.characters.size()-1) {
                            nextBtn.setEnabled(false);
                            prevBtn.setEnabled(true);
                        }else{
                            nextBtn.setEnabled(true);
                            prevBtn.setEnabled(true);
                        }
                    }
                    break;
                }
            }
        }
         public void deleteCharacter(View view) {
            deleteCharacter();
        }

        public void deleteCharacter(){
            Button prevBtn = (Button) findViewById(R.id.btnPrevContact);
            Button nextBtn = (Button) findViewById(R.id.btnNextContact);
            CdbHelper.deleteCharacter(this.characters.get(this.characterIndex).getId());
            if(characters.size()==1) {
                CdbHelper.createCharacter("UNKNOWN", "UNKNOWN", "UNKNOWN", 0, 0, 0, 0, 0, 0, 0);
            }
            this.characters = CdbHelper.getAllCharacters();
            if(characters.size() == 1){
                prevBtn.setEnabled(false);
                nextBtn.setEnabled(false);
            }else if (this.characterIndex == 0) {
                prevBtn.setEnabled(false);
                nextBtn.setEnabled(true);
            }else if (this.characterIndex == this.characters.size()-1) {
                nextBtn.setEnabled(false);
                prevBtn.setEnabled(true);
            }else if (this.characterIndex >= this.characters.size())
            {
                this.characterIndex--;
            }
            displayCharacter(this.characters.get(characterIndex));
        }
    }

