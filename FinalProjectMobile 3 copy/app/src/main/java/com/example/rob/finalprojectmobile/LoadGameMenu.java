package com.example.rob.finalprojectmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LoadGameMenu extends Activity {

    SavingSystem<Character> savSys;
    Character playerCharacter = new Character();
    ArrayList<String> saveGameArray = new ArrayList<String>();

    ArrayAdapter<String> loadGameAdapter;


    String tacos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game_menu);

        final ListView gameSaves = (ListView) findViewById(R.id.loadGameList);

        //TODO: Load one or more of one or several saved games.

        //"String" is a temporary placeholder for the saved game object #SHAINE

        savSys = new SavingSystem<Character>(this.getApplicationContext());

        //this wont actually be there, it will pull saves from the "disk"
        //saveGameArray.add("test1");
        //saveGameArray.add("test2");
        //saveGameArray.add("test3");
        //saveGameArray.add("test4");
        savSys.printDirectory(this.getApplicationContext().getFilesDir());
        saveGameArray = savSys.printDirectoryString(this.getApplicationContext().getFilesDir());
        //playerCharacter = savSys.mobileDeserial("George", "");

        loadGameAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                saveGameArray
        );
        gameSaves.setAdapter(loadGameAdapter);

        //onclick listener for the arrayList
        // when clicked on name appears at top
        gameSaves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                 tacos = loadGameAdapter.getItem(position);

                //Context button = loadGameAdapter.getContext();

                String charName;
                System.out.println("... tacos "+ tacos +"...");
                //charName = gameSaves.getSelectedItem().toString();
                //System.out.println("...obj "+o+" ...");
                //System.out.println("...nam "+charName+" ...");

                TextView nameTV = (TextView) findViewById(R.id.NameChar);
                nameTV.setText((tacos));
            }
        });


        // click on a character, then click load or delete to load or delete




    }



    public void removeSave(View view){
        //TODO: this will remove the save from the list, deleting the character and all its related data

        System.out.println(tacos);

        if (tacos.equals("")){

            System.out.println("no data Selected");
        }else{
            //delete the data
            savSys.mobileDeleteSave(tacos);
            saveGameArray.remove(tacos);
            loadGameAdapter.notifyDataSetChanged();
        }


    }

    public  void launchSave(View view){
        //TODO: This will launch the saved game

        if (tacos.equals("")){

            System.out.println("no data Selected");
        }else{
            //not sure if this is 100% correct #Shaine
            Intent startTheGameAlready = new Intent(LoadGameMenu.this, mainScreen.class);
            startTheGameAlready.putExtra("name", tacos);
            startActivity(startTheGameAlready);
        }

    }

}
