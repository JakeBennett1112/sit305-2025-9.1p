package com.example.passtask71;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passtask71.data.DatabaseHelper;
import com.example.passtask71.model.Advert;

import java.util.List;

public class ShowAllLostAndFoundItems extends AppCompatActivity {
    // create variables
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    AdvertAdapter advertAdapter;

    // oncreate method when activity is created and set the layout for the all lost and found items

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_lost_and_found_items);

        // initialise recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // arrange items in a vertical list

        // initialise database helper
        dbHelper = new DatabaseHelper(this); // create instance within this file so we can interact with the datbase and fetch the list of lost and found items
        List<Advert> allItems = dbHelper.getAllItems(); // get all items from database and return as a list of all Adverts

        // set up adapter
        advertAdapter = new AdvertAdapter(this, allItems); // pass items into adapter so it can convert the objects into a view
        recyclerView.setAdapter(advertAdapter); // display each advert within the list of items
    }
}
