package com.example.passtask71;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // create button variable for new advert
    private Button newAdvertButton;
    private Button showItemsButtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get id of buttons
        newAdvertButton = findViewById(R.id.NewAdvertButton);
        showItemsButtton = findViewById(R.id.ShowItemsButton);

        // create on click listener for when someone clicks the button
        // Use an intent to start and open another screen, in this case the new activity screen
        newAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateNewAdvertActivity.class);
                startActivity(intent);
            }
        });

        // Create on click and intent to move to show all lost and found items
        showItemsButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAllLostAndFoundItems.class);
                startActivity(intent);
            }
        });
    }
}