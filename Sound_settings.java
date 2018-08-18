package com.apps.jinstin.emergencyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Sound_settings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundsettings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email app author for help", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"emergencyapphelp@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Help");
                intent.putExtra(Intent.EXTRA_TEXT, "Please feel free to email me with your questions, concerns or anything else. I'll do my best to help you as soon as I see your email. I can also help report your emergency to authorities for those based in the U.S. For those outside of the U.S, I can publish your event to the Internet if you request.");
                startActivity(Intent.createChooser(intent, ""));
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button button = (Button) findViewById(R.id.silence);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, 0, 0);
                Toast.makeText(getApplicationContext(), "Silent Mode Activated", Toast.LENGTH_LONG).show();
            }
        });

        Button button2 = (Button) findViewById(R.id.unsilence);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // AudioManager audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
               // audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
               // Toast.makeText(getApplicationContext(), "Normal Mode Activated", Toast.LENGTH_LONG).show();
                Intent messagesettings = new Intent(Settings.ACTION_SOUND_SETTINGS);
                startActivity(messagesettings);
            }});

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
        getMenuInflater().inflate(R.menu.tips, menu);
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
            Intent myIntent = new Intent(this, Preferences.class);
            Sound_settings.this.startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_location) {
            Intent myIntent = new Intent(this, Location_update.class);
            Sound_settings.this.startActivity(myIntent);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_email) {

        } else if (id == R.id.nav_instructions) {
            Intent myIntent = new Intent(this, Instruction.class);
            Sound_settings.this.startActivity(myIntent);
        } else if (id == R.id.nav_tips) {
            Intent myIntent = new Intent(this, Tips.class);
            Sound_settings.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
