package com.example.rob.finalprojectmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class CharacterDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_FILENAME = "characters.db";
    public static final String TABLE_NAME = "Characters";

    // don't forget to use the column name '_id' for your primary key
    public static final String CREATE_STATEMENT = "CREATE TABLE " + TABLE_NAME + "(" +
            "  _id integer primary key autoincrement, " +
            "  name text not null," +
            "  race text not null," +
            "  classID text not null," +
            "  level text not null," +
            "  intel text not null," +
            "  vit text not null," +
            "  wil text not null," +
            "  str text not null," +
            "  end text not null," +
            "  luk text not null" +
            ")";
    public static final String DROP_STATEMENT = "DROP TABLE " + TABLE_NAME;

    public CharacterDBHelper(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // the implementation below is adequate for the first version
        // however, if we change our table at all, we'd need to execute code to move the data
        // to the new table structure, then delete the old tables (renaming the new ones)

        // the current version destroys all existing data
        database.execSQL(DROP_STATEMENT);
        database.execSQL(CREATE_STATEMENT);
    }

    public Enemy createCharacter(String name, String race, String classID, int lvl, int intel, int vit, int wil, int str, int luk,int end) {
        // create the object
        Enemy c = new Enemy(name, race, classID, lvl, intel, vit, wil, str, luk, end);
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // insert the data into the database
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("race", race);
        values.put("classID", classID);
        values.put("level", lvl);
        values.put("intel", intel);
        values.put("vit", vit);
        values.put("wil", wil);
        values.put("str", str);
        values.put("luk", luk);
        values.put("end", end);
        long id = database.insert(TABLE_NAME, null, values);
        // assign the Id of the new database row as the Id of the object
        c.setId(id);

        return c;
    }

    public Enemy getCharacter(long id) {
        Enemy c = null;

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // retrieve the contact from the database
        String[] columns = new String[] { "_id", "name", "race", "classID","level","intel","vit","wil","str","luk","end"};
        Cursor cursor = database.query(TABLE_NAME, columns, "_id = ?", new String[] { "" + id }, "", "", "");
        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();
            String name = cursor.getString(0);
            String race = cursor.getString(1);
            String classID = cursor.getString(2);
            int level = Integer.parseInt(cursor.getString(3));
            int intel = Integer.parseInt(cursor.getString(4));
            int vit = Integer.parseInt(cursor.getString(5));
            int wil = Integer.parseInt(cursor.getString(6));
            int str = Integer.parseInt(cursor.getString(7));
            int luk = Integer.parseInt(cursor.getString(8));
            int end = Integer.parseInt(cursor.getString(9));

            c = new Enemy(name, race, classID, level,intel, vit, wil, str, luk, end);
            c.setId(id);
        }

        Log.i("DatabaseAccess", "getProduct(" + id + "):  product: " + c.getName());

        return c;
    }

    public ArrayList<Enemy> getAllCharacters() {
        ArrayList<Enemy> characters = new ArrayList<Enemy>();

        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // retrieve the contact from the database
        String[] columns = new String[] { "_id", "name", "race", "classID","level","intel","vit","wil","str","luk","end"};
        Cursor cursor = database.query(TABLE_NAME, columns, "", new String[]{}, "", "", "");
        cursor.moveToFirst();
        do {
            // collect the contact data, and place it into a contact object
            long id = Long.parseLong(cursor.getString(0));
            String name = cursor.getString(1);
            String race = cursor.getString(2);
            String classID = cursor.getString(3);
            int level = Integer.parseInt(cursor.getString(4));
            int intel = Integer.parseInt(cursor.getString(5));
            int vit = Integer.parseInt(cursor.getString(6));
            int wil = Integer.parseInt(cursor.getString(7));
            int str = Integer.parseInt(cursor.getString(8));
            int luk = Integer.parseInt(cursor.getString(9));
            int end = Integer.parseInt(cursor.getString(10));

            Enemy character = new Enemy(name, race, classID, level, intel, vit, wil, str, luk, end);
            character.setId(id);

            // add the current contact to the list
            characters.add(character);

            // advance to the next row in the results
            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("DatabaseAccess", "getAllProductss():  num: " + characters.size());

        return characters;
    }
    public boolean updateCharacter(Enemy c) {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // update the data in the database
        ContentValues values = new ContentValues();
        values.put("name", c.getName());
        values.put("race", c.getRace());
        values.put("classID", c.getClassID());
        values.put("level", c.getLVL());
        values.put("intel", c.getINT());
        values.put("vit", c.getVIT());
        values.put("wil", c.getWIL());
        values.put("str", c.getSTR());
        values.put("luk", c.getLUK());
        values.put("end", c.getEND());
        int numRowsAffected = database.update(TABLE_NAME, values, "_id = ?", new String[] { "" + c.getId() });

        Log.i("DatabaseAccess", "updateProduct(" + c.getName() + "):  numRowsAffected: " + numRowsAffected);

        // verify that the contact was updated successfully
        return (numRowsAffected == 1);
    }

    public boolean deleteCharacter(long id) {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // delete the contact
        int numRowsAffected = database.delete(TABLE_NAME, "_id = ?", new String[] { "" + id });

        Log.i("DatabaseAccess", "deleteProduct(" + id + "):  numRowsAffected: " + numRowsAffected);

        // verify that the contact was deleted successfully
        return (numRowsAffected == 1);
    }

    public void deleteAllCharacters() {
        // obtain a database connection
        SQLiteDatabase database = this.getWritableDatabase();

        // delete the contact
        int numRowsAffected = database.delete(TABLE_NAME, "", new String[] {});

        Log.i("DatabaseAccess", "deleteAllProducts():  numRowsAffected: " + numRowsAffected);
    }
}

