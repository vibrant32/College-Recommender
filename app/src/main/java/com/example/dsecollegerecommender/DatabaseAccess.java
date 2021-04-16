package com.example.dsecollegerecommender;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    Cursor cursor;
    String dtecode, collegename, location, branch, category = "";
    ArrayList<Model> dataholder = new ArrayList<Model>();

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }
    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    /**
     * Read all colleeges from the database.
     *
     * @return a List of quotes
     */

    public Cursor getColleges(String per, String cat, HashMap<String,String> cities) {
        //List<String> list = new ArrayList<>();
        String pune = cities.get("city1");
        String amra = cities.get("city2");
        String ahmed = cities.get("city3");
        String nashik = cities.get("city4");
        String nanded = cities.get("city5");
        String aurang = cities.get("city6");
        String nagpur = cities.get("city7");
        String mumbai = cities.get("city8");
        String sangli = cities.get("city9");
        String[] vals = new String[cities.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : cities.entrySet()) {
            vals[index] = entry.getValue();
            index++;
        }
        if(pune == "Pune" || amra == "Amravati" || ahmed == "Ahmednagar" || nashik == "Nashik" || nanded == "Nanded" || aurang == "Aurangbad" || nagpur == "Nagpur" || mumbai == "Mumbai" || sangli == "Sangli") {
            cursor = database.rawQuery("SELECT * FROM Colleges WHERE ("+cat+" BETWEEN "+(Float.parseFloat(per)-5)+" AND " +(Float.parseFloat(per)+5)+") AND city IN (?, ?, ?, ?, ?, ?, ?, ?, ?) ORDER BY "+cat+" DESC LIMIT 50 ", vals);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                dtecode = cursor.getString(1);
                collegename = cursor.getString(2);
                location = cursor.getString(3);
                branch = cursor.getString(4);
                if(cat.equals("Open")) {
                    category = cursor.getString(5);
                }
                if(cat.equals("Obc")) {
                    category = cursor.getString(6);
                }
                if(cat.equals("Sc")) {
                    category = cursor.getString(7);
                }
                if(cat.equals("St")) {
                    category = cursor.getString(8);
                }
                if(cat.equals("Vjnt")) {
                    category = cursor.getString(9);
                }
                if(cat.equals("Ews")) {
                    category = cursor.getString(10);
                }
                Model obj = new Model(dtecode,collegename,location,branch,category);
                dataholder.add(obj);
                //Log.v("Cat", c);
                cursor.moveToNext();
            }
            //cursor.close();
        }
        //Log.v("Rows-", category.getClass().getSimpleName());
        return cursor;
    }
}
