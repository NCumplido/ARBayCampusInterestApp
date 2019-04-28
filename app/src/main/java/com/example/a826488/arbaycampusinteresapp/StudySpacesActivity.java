package com.example.a826488.arbaycampusinteresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StudySpacesActivity extends AppCompatActivity {
    private static final String TAG = StudySpacesActivity.class.getSimpleName();

    Button m_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_spaces);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
// Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

     addListenerOnButton();

    }

    public void addListenerOnButton() {

        m_button = (Button) findViewById(R.id.button5);

        m_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                m_button.setText(TAG);
                //Do something here...
                Intent studySpacesIntent = new Intent(getApplicationContext(), StudySpacesARActivity.class);
                //studySpacesIntent.putExtra("Filepath", filePath);
                startActivity(studySpacesIntent);
            }

        });

    }
}
