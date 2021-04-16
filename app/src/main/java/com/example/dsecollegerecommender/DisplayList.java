package com.example.dsecollegerecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayList extends AppCompatActivity {

    RecyclerView recyclerView;
    Bundle bundle;
    public Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_display_list);

        recyclerView = (RecyclerView)findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bundle = getIntent().getExtras();
        HashMap<String, String> cities = new HashMap<>();

        Boolean city1 = getIntent().getExtras().getBoolean("Pune");
        Boolean city2 = getIntent().getExtras().getBoolean("Amravati");
        Boolean city3 = getIntent().getExtras().getBoolean("Ahmednagar");
        Boolean city4 = getIntent().getExtras().getBoolean("Nashik");
        Boolean city5 = getIntent().getExtras().getBoolean("Nanded");
        Boolean city6 = getIntent().getExtras().getBoolean("Aurangabad");
        Boolean city7 = getIntent().getExtras().getBoolean("Nagpur");
        Boolean city8 = getIntent().getExtras().getBoolean("Mumbai");
        Boolean city9 = getIntent().getExtras().getBoolean("Sangli");

        if(city1) {
            cities.put("city1", "Pune");
        }
        if(city2) {
            cities.put("city2", "Amravati");
        }
        if(city3) {
            cities.put("city3", "Ahmednagar");
        }
        if(city4) {
            cities.put("city4", "Nashik");
        }
        if(city5) {
            cities.put("city5", "Nanded");
        }
        if (city6) {
            cities.put("city6", "Aurangabad");
        }
        if(city7) {
            cities.put("city7", "Nagpur");
        }
        if(city8) {
            cities.put("city8", "Mumbai");
        }
        if(city9) {
            cities.put("city9", "Sangli");
        }
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        cursor = databaseAccess.getColleges((String)bundle.get("Percentage"),(String)bundle.get("Category"),cities);
        cursor.moveToFirst();

        MyAdapter adapter = new MyAdapter(databaseAccess.dataholder);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Generated "+cursor.getCount()+" Colleges For You!", Toast.LENGTH_SHORT).show();
        databaseAccess.close();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        MyAdapter adapter = new MyAdapter(databaseAccess.dataholder);
        adapter.clear();
    }
}

