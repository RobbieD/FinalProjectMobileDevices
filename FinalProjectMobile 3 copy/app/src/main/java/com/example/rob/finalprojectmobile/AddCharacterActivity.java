package com.example.rob.finalprojectmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCharacterActivity extends Activity {
        int lvlV, intV, vitV, wilV, strV, lukV, endV, ptsV, expV;
        TextView lvlTV,intTV, vitTV, wilTV, strTV, lukTV, endTV, ptsTV,expTV;
        Character playerCharacter = new Character();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_character);
            updateUI();


        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_add_product, menu);
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
        public void updateUI(){
            lvlV = 0;
            lvlTV = (TextView) findViewById(R.id.lvlP);
            lvlTV.setText(lvlV+"");

            intV = 0;
            intTV = (TextView) findViewById(R.id.intP);
            intTV.setText(intV+"");

            vitV = 0;
            vitTV = (TextView) findViewById(R.id.vitP);
            vitTV.setText(vitV+"");

            wilV = 0;
            wilTV = (TextView) findViewById(R.id.wilP);
            wilTV.setText(wilV+"");

            strV = 0;
            strTV = (TextView) findViewById(R.id.strP);
            strTV.setText(strV+"");

            lukV = 0;
            lukTV = (TextView) findViewById(R.id.lukP);
            lukTV.setText(lukV+"");

            endV = 0;
            endTV = (TextView) findViewById(R.id.endP);
            endTV.setText(endV+"");

        }

        public void lvlP(View view){
            lvlTV.setText((Integer.parseInt(lvlTV.getText().toString())+1)+"");
        }

        public void expP(View view){
            expTV.setText((Integer.parseInt(expTV.getText().toString())+10)+"");
        }

        public void intP(View view){
            //if pts > 0
            //find pts in character -1
            //find int in character +1
            //update ui
            intTV.setText((Integer.parseInt(intTV.getText().toString()) + 1) + "");
        }

        public void vitP(View view){
            vitTV.setText((Integer.parseInt(vitTV.getText().toString())+1)+"");
        }

        public void wilP(View view){
            wilTV.setText((Integer.parseInt(wilTV.getText().toString())+1)+"");
        }

        public void strP(View view){
            strTV.setText((Integer.parseInt(strTV.getText().toString())+1)+"");
        }

        public void lukP(View view) {
            lukTV.setText((Integer.parseInt(lukTV.getText().toString())+1)+"");
        }

        public void endP(View view){
            endTV.setText((Integer.parseInt(endTV.getText().toString())+1)+"");
        }

        public void ptsP(View view){
            ptsTV.setText((Integer.parseInt(ptsTV.getText().toString())+1)+"");
        }

        public void cancel(View view){
            EditText nameText = (EditText) findViewById(R.id.nameField);
            EditText raceText = (EditText) findViewById(R.id.raceField);
            EditText classText = (EditText) findViewById(R.id.classField);
            nameText.setText("");
            raceText.setText("");
            classText.setText("");
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newProdName", "N/A");
            resultIntent.putExtra("newProdDescript", "N/A");
            resultIntent.putExtra("newProdPrice", 0.0);
            setResult(2, resultIntent);
            finish();
        }

    /*public void addProduct(View view){
        addProduct();
    }

    public void addProduct(){
        EditText nameText = (EditText) findViewById(R.id.nameField);
        EditText descriptText = (EditText) findViewById(R.id.descriptField);
        EditText priceText = (EditText) findViewById(R.id.priceField);
        double price = 0.0;
        try {
            price = Double.parseDouble(priceText.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        prod = new Product(nameText.getText().toString(),descriptText.getText().toString(),price);
        nameText.setText("");
        descriptText.setText("");
        priceText.setText("");

        Intent resultIntent = new Intent();
        resultIntent.putExtra("newProdName", prod.getName());
        resultIntent.putExtra("newProdDescript", prod.getDescription());
        resultIntent.putExtra("newProdPrice", prod.getPrice());
        setResult(1, resultIntent);
        finish();
    }*/

        public void addCharacter(View view){
            addCharacter();
        }

        public void addCharacter(){
            EditText nameText = (EditText) findViewById(R.id.nameField);
            EditText classText = (EditText) findViewById(R.id.classField);
            EditText raceText = (EditText) findViewById(R.id.raceField);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("newCharName", nameText.getText().toString());
            resultIntent.putExtra("newCharClass", classText.getText().toString());
            resultIntent.putExtra("newCharRace", raceText.getText().toString());
            nameText.setText("");
            classText.setText("");
            raceText.setText("");
            resultIntent.putExtra("newCharLevel", Integer.parseInt(lvlTV.getText().toString()));
            resultIntent.putExtra("newCharIntellect", Integer.parseInt(intTV.getText().toString()));
            resultIntent.putExtra("newCharVitality", Integer.parseInt(vitTV.getText().toString()));
            resultIntent.putExtra("newCharWillpower", Integer.parseInt(wilTV.getText().toString()));
            resultIntent.putExtra("newCharStrength", Integer.parseInt(strTV.getText().toString()));
            resultIntent.putExtra("newCharLuck", Integer.parseInt(lukTV.getText().toString()));
            resultIntent.putExtra("newCharEndurance", Integer.parseInt(endTV.getText().toString()));
           // resultIntent.putExtra("newCharPoints", Integer.parseInt(ptsTV.getText().toString()));
            //resultIntent.putExtra("newCharExp", Integer.parseInt(expTV.getText().toString()));

            setResult(1, resultIntent);
            finish();
        }
    }
