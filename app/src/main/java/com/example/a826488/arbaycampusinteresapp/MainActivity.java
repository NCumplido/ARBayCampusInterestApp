package com.example.a826488.arbaycampusinteresapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//TODO: Privacy statements clickable
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_how_to) {
            Intent howTo = new Intent(this, HowTo.class);
            startActivity(howTo);
        } else if (id == R.id.nav_2d_map) {
            Log.d("Map", "Button tapped");
            Intent map = new Intent(this, MapsActivity.class);
            Log.d("Map", "Intent created");
            startActivity(map);
        } else if (id == R.id.nav_scan_sign) {
            Intent arView = new Intent(this, AugmentedImageActivity.class);
            startActivity(arView);
        } else if (id == R.id.nav_floor_plans) {
            Intent floorPlansIntent = new Intent(this, FloorPlansActivity.class);
            startActivity(floorPlansIntent);
        } else if (id == R.id.nav_about) {
            Intent about = new Intent(this, AboutActivity.class);
            startActivity(about);
        } else if (id == R.id.nav_contact) {
            Intent contact = new Intent(this, ContactActivity.class);
            startActivity(contact);
        } else if (id == R.id.nav_ar_directions){
            Intent arDirections = new Intent(this, ArDirectionsActivity.class);
            startActivity(arDirections);
        } else if (id == R.id.nav_study_spaces){
            Intent studySpacesActivity = new Intent(this, StudySpacesActivity.class);
            startActivity(studySpacesActivity);
        }/* else if (id == R.id.nav_study_spaces){
            Intent aRStudySpacesActivity = new Intent(this, ARStudySpacesActivity.class);
            startActivity(aRStudySpacesActivity);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
