package com.example.rob.finalprojectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class NewGameMenu extends Activity {

    Spinner classSpinner;
    Spinner raceSpinner;

    final int rq =1101500;

    Character player;


    private String blockCharacterSet = "~#^|$%&*!:<>,.{}[]()`@/\'\"\\;";

    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_menu);

        EditText editText = (EditText) findViewById(R.id.charName);
        editText.setFilters(new InputFilter[] {filter});

        classSpinner = (Spinner)findViewById(R.id.spinnerClass);
        ArrayAdapter<CharSequence> classAdapter = ArrayAdapter.createFromResource(this, R.array.classes, R.layout.support_simple_spinner_dropdown_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        raceSpinner = (Spinner)findViewById(R.id.spinnerRace);
        ArrayAdapter<CharSequence> raceAdapter = ArrayAdapter.createFromResource(this, R.array.races, R.layout.support_simple_spinner_dropdown_item);
        raceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(raceAdapter);


    }

    //TODO: Starts a new game # Shaine


    public void submitChar(View view){
        String charClass = classSpinner.getSelectedItem().toString();
        String charRace = raceSpinner.getSelectedItem().toString();
        EditText name = (EditText) findViewById(R.id.charName);
        SavingSystem<Character> savSys = new SavingSystem<Character>(this.getApplicationContext());
        String charName = name.getText().toString();

        player = new Character(charName, charClass, charRace);
        savSys.mobileSerial(player,player.getName(),"");


        Intent submitCharIntent = new Intent(NewGameMenu.this, StatPage.class);
        submitCharIntent.putExtra("name", player.getName());

        startActivityForResult(submitCharIntent, rq);



        finish();
    }


}
