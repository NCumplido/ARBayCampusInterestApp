package com.example.a826488.arbaycampusinteresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FloorPlansActivity extends AppCompatActivity {
    Button m_floor0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plans);


        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
// Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        m_floor0 = findViewById(R.id.btn_floor0);
        m_floor0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent arView = new Intent(getApplicationContext(), CoFoFloor0.class);
                startActivity(arView);
            }
        });

    }

}
