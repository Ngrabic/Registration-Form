package com.example.registrationform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SecondaryActivity extends AppCompatActivity {

     public static   ArrayList<People> peoples=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        RecyclerView peopleList = findViewById(R.id.peopleListId);



        PeopleRecViewAdatper adatper=new PeopleRecViewAdatper(this);
        adatper.setPeoples(peoples);

        peopleList.setAdapter(adatper);
        peopleList.setLayoutManager(new GridLayoutManager(this,2));


    }

}