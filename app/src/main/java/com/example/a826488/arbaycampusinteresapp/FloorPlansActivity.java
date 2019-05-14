package com.example.a826488.arbaycampusinteresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FloorPlansActivity extends AppCompatActivity {
    Button m_floor0, m_floor1, m_floor2, m_floor3, m_floor4;

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
                // Move to AR Activity
                Intent floor0ArIntent = new Intent(getApplicationContext(), CofoPlansArActivity.class);
                floor0ArIntent.putExtra("floorIdentifier", "floor0");
                startActivity(floor0ArIntent);
            }
        });

        m_floor1 = findViewById(R.id.btn_floor_1);
        m_floor1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Move to AR Activity
                Intent floor1ArIntent = new Intent(getApplicationContext(), CofoPlansArActivity.class);
                floor1ArIntent.putExtra("floorIdentifier", "floor1");
                startActivity(floor1ArIntent);
            }
        });

        m_floor2 = findViewById(R.id.btn_floor2);
        m_floor2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Move to AR Activity
                Intent floor2ArIntent = new Intent(getApplicationContext(), CofoPlansArActivity.class);
                floor2ArIntent.putExtra("floorIdentifier", "floor2");
                startActivity(floor2ArIntent);
            }
        });

        m_floor3 = findViewById(R.id.btn_floor3);
        m_floor3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Move to AR Activity
                Intent floor3ArIntent = new Intent(getApplicationContext(), CofoPlansArActivity.class);
                floor3ArIntent.putExtra("floorIdentifier", "floor3");
                startActivity(floor3ArIntent);
            }
        });

        m_floor4 = findViewById(R.id.btn_floor4);
        m_floor4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Move to AR Activity
                Intent floor4ArIntent = new Intent(getApplicationContext(), CofoPlansArActivity.class);
                floor4ArIntent.putExtra("floorIdentifier", "floor4");
                startActivity(floor4ArIntent);
            }
        });


    }

}
