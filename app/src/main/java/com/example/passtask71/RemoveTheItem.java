package com.example.passtask71;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.passtask71.data.DatabaseHelper;

public class RemoveTheItem extends AppCompatActivity {
    private TextView itemText, dateText, locationText;
    private Button removeButton;
    private DatabaseHelper db;

    String item, date, location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_the_item);

        // Initialise database
        db = new DatabaseHelper(this);

        // Link UI elements in xml
        itemText = findViewById(R.id.removeText1);
        dateText = findViewById(R.id.daysAgoText);
        locationText = findViewById(R.id.whereText);
        removeButton = findViewById(R.id.removeButton);

        // get the item data from the intent from showAllLostAndFoundItems
        item = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        location = getIntent().getStringExtra("location");

        // set the text views in the xml with the item data
        itemText.setText(item);
        dateText.setText(date);
        locationText.setText(location);

        // Remove item when remove button is clicked
        removeButton.setOnClickListener(v -> {
            db.deleteItemByName(item);
            Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
