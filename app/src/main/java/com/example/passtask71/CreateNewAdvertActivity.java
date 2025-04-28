package com.example.passtask71;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.passtask71.data.DatabaseHelper;
import com.example.passtask71.model.Advert;

public class CreateNewAdvertActivity extends AppCompatActivity {
    // create variables so we can edit information, change radio button and save
    private EditText nameInput, phoneInput, descriptionInput, dateInput, locationInput;
    private RadioButton lostRadio, foundRadio;
    private Button saveButton;
    DatabaseHelper db;

    // on create for when the screen and activity is loaded

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_a_new_advert); // set the create a new advert file to be showing on the screen

        // Initialise the views
        nameInput = findViewById(R.id.NameInput);
        phoneInput = findViewById(R.id.phoneInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        dateInput = findViewById(R.id.dateInput);
        locationInput = findViewById(R.id.locationInput);
        lostRadio = findViewById(R.id.LostRadioButton);
        foundRadio = findViewById(R.id.FoundRadioButton);
        saveButton = findViewById(R.id.saveButton);
        db = new DatabaseHelper(this);

            // Button logic for when we want to save an adverts details
            // After we click the save button
        saveButton.setOnClickListener(v -> {
            // Convert all entered details to a string
            String name = nameInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String date = dateInput.getText().toString();
            String location = locationInput.getText().toString();
            // check to see if lost or found has been chosen for radio button
            String postType = lostRadio.isChecked() ? "Lost" : foundRadio.isChecked() ? "Found" : "";

            // make sure all inputs have been filled out. Error handling
            if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty() || postType.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save the information into the database
                Toast.makeText(this, "Advert saved!", Toast.LENGTH_SHORT).show();
                long result = db.insertItem(new Advert(name, description, date, location, Integer.parseInt(phone)));
            }
        });
    }
}
