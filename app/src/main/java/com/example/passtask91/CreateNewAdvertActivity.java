package com.example.passtask91;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.passtask91.data.DatabaseHelper;
import com.example.passtask91.model.Advert;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.FusedLocationProviderClient;

public class CreateNewAdvertActivity extends AppCompatActivity {
    // create variables so we can edit information, change radio button and save
    private EditText nameInput, phoneInput, descriptionInput, dateInput, locationInput;
    private RadioButton lostRadio, foundRadio;
    private Button saveButton ;
    private Button currentLocationButton;
    private DatabaseHelper db;

    // variable for location check. Match permission against results
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient; // access devices location

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
        currentLocationButton = findViewById(R.id.currentLocationButton);
        db = new DatabaseHelper(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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

        // check to see if we have permission to access device's location
        currentLocationButton.setOnClickListener(v -> checkLocationPermission());

    }

    // check location permission
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        // Ask for permission
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getCurrentLocation();  // get location when granted
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show();
            return;
        }
        // set coordinates in location input field
        fusedLocationClient.getCurrentLocation(
                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                null
        ).addOnSuccessListener(this, location -> {
                    if (location != null) {
                        double lat = location.getLatitude();
                        double lng = location.getLongitude();
                        String locationString = "Lat: " + lat + ", Lng: " + lng;
                        locationInput.setText(locationString);
                    } else {
                        Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation(); // granted
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show(); // denied
            }
        }
    }
}

