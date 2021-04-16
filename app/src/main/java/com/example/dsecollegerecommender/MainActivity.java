package com.example.dsecollegerecommender;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button submitData;
    EditText percentage;
    public String pData;
    RadioGroup categories, categories2;
    public RadioButton rbtn;
    AlertDialog.Builder builder;
    CheckBox Pune, Amravati, Ahmednagar, Nashik, Nanded, Aurangabad, Nagpur, Mumbai, Sangli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        percentage = (EditText)findViewById(R.id.studentpercentage);
        submitData = (Button)findViewById(R.id.submitdetails);
        categories = (RadioGroup)findViewById(R.id.radiogrpOne);
        categories2 = (RadioGroup)findViewById(R.id.radiogrpTwo);
        Pune = (CheckBox)findViewById(R.id.checkBoxOne);
        Amravati = (CheckBox)findViewById(R.id.checkBoxTwo);
        Ahmednagar = (CheckBox)findViewById(R.id.checkBoxThree);
        Nashik = (CheckBox)findViewById(R.id.checkBoxFour);
        Nanded = (CheckBox)findViewById(R.id.checkBoxFive);
        Aurangabad = (CheckBox)findViewById(R.id.checkBoxSix);
        Nagpur = (CheckBox)findViewById(R.id.checkBoxSeven);
        Mumbai = (CheckBox)findViewById(R.id.checkBoxEight);
        Sangli = (CheckBox)findViewById(R.id.checkboxNine);

        categories.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i != -1)
                    categories2.check(-1);
                    int selectedBtn = categories.getCheckedRadioButtonId();
                    rbtn = (RadioButton)findViewById(selectedBtn);
            }
        });
        categories2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if(i != -1)
                    categories.check(-1);
                    int selectedBtn = categories2.getCheckedRadioButtonId();
                    rbtn = (RadioButton)findViewById(selectedBtn);
            }
        });

        builder = new AlertDialog.Builder(this);

        submitData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pData = percentage.getText().toString();
                        if (pData.equals(""))  {
                            percentage.setError("required!");
                        } else if(categories.getCheckedRadioButtonId() == -1 && categories2.getCheckedRadioButtonId() == -1) {
                                builder.setMessage("Please Select a Category!").setCancelable(false)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.setTitle("Error");
                            alert.show();
                        } else if((!Pune.isChecked()) && (!Amravati.isChecked()) && (!Ahmednagar.isChecked()) && (!Nashik.isChecked()) && (!Nanded.isChecked()) && (!Aurangabad.isChecked()) && (!Nagpur.isChecked()) && (!Mumbai.isChecked()) && (!Sangli.isChecked())) {
                            builder.setMessage("Please Select At Least One City!").setCancelable(false)
                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.setTitle("Error");
                            alert.show();
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), DisplayList.class);
                            intent.putExtra("Percentage", pData);
                            String caste = rbtn.getText().toString().substring(0,1).toUpperCase() + rbtn.getText().toString().substring(1).toLowerCase();
                            intent.putExtra("Category", caste);
                            intent.putExtra("Pune", Pune.isChecked());
                            intent.putExtra("Amravati", Amravati.isChecked());
                            intent.putExtra("Ahmednagar", Ahmednagar.isChecked());
                            intent.putExtra("Nashik", Nashik.isChecked());
                            intent.putExtra("Nanded", Nanded.isChecked());
                            intent.putExtra("Aurangabad", Aurangabad.isChecked());
                            intent.putExtra("Nagpur", Nagpur.isChecked());
                            intent.putExtra("Mumbai", Mumbai.isChecked());
                            intent.putExtra("Sangli",Sangli.isChecked());
                            startActivity(intent);
                        }
                    }
                }
        );

    }

}