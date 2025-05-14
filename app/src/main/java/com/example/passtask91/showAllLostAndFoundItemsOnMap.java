package com.example.passtask91;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.passtask91.data.DatabaseHelper;
import com.example.passtask91.model.Advert;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

// extend woth fragment activity so we can use SupportMapFragment
// implement on map ready callback for GoogleMap object
public class showAllLostAndFoundItemsOnMap extends FragmentActivity implements OnMapReadyCallback {

    // variables for marker and map control and retrieve database items
    private GoogleMap mMap;
    private DatabaseHelper db;

    // load xml file
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_lost_and_found_items_on_map);

        // initialise database
        db = new DatabaseHelper(this);

        // Load map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    // Access google map objects with mMap
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // get the items from the database
        List<Advert> items = db.getAllItems();

        // For each item"
            // Get the latitude and longitude
            // Add the item to the map with its name and location as text
        for (Advert item : items) {
            double lat = item.getLatitude();
            double lng = item.getLongitude();

            LatLng position = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions()
                    .position(position)
                    .title(item.getItem_name())
                    .snippet(item.getItem_location())
            );
        }

        // Move camera to first item if available
        if (!items.isEmpty()) {
            Advert first = items.get(0);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(first.getLatitude(), first.getLongitude()), 12));
        }

    }
}
