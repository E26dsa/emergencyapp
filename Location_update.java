package com.apps.jinstin.emergencyapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.telephony.SmsManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Location_update extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler = new Handler();
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        startTimer();

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


        Button button = (Button) findViewById(R.id.gpsbutton);
        button.setOnClickListener(new View.OnClickListener() {
                                      @SuppressLint("MissingPermission")
                                      public void onClick(View v) {
                                          ActivityCompat.requestPermissions(Location_update.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                                      }
                                  });
        Button button2 = (Button) findViewById(R.id.saveaddress);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Location_update.this, "Address saved!", Toast.LENGTH_LONG).show();
            }
        });

        }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    int m_interval = 5000; // 5 seconds by default, can be changed later
                    Handler m_handler = new Handler();

                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                    if (networkInfo!=null)
                    {if( networkInfo.isConnected()) {

                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        LocationListener ll = new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                                try {
                                    LocationManager locationManager2 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                                    Location location2 = locationManager2.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                    Geocoder geocoder = new Geocoder(getBaseContext());
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    Address obj = addresses.get(0);
                                    String add = obj.getAddressLine(0);
                                    add = add + "," + obj.getAdminArea();
                                    EditText textbox = (EditText) findViewById(R.id.address);
                                    textbox.setText(add);

                                } catch (IOException e) {
                                    e.printStackTrace();


                                }
                            }

                            @Override
                            public void onStatusChanged(String s, int i, Bundle bundle) {
                            }

                            @Override
                            public void onProviderEnabled(String s) {
                            }

                            @Override
                            public void onProviderDisabled(String s) {
                            }
                        };
                        LocationListener ll2 = new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location) {
                            }

                            @Override
                            public void onStatusChanged(String s, int i, Bundle bundle) {
                            }

                            @Override
                            public void onProviderEnabled(String s) {
                            }

                            @Override
                            public void onProviderDisabled(String s) {
                            }
                        };
                        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, ll2, null);
                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (location != null) {

                            Geocoder geocoder2 = new Geocoder(getBaseContext());
                            List<Address> addresses2 = null;
                            try {
                                addresses2 = geocoder2.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                Address obj2 = addresses2.get(0);
                                String add2 = obj2.getAddressLine(0);
                                add2 = add2 + "," + obj2.getAdminArea();
                                EditText textbox = (EditText) findViewById(R.id.address);
                                textbox.setText(add2);

                            } catch (IOException e1) {
                                e1.printStackTrace();


                            }

                        } else {
                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
                            AlertDialog.Builder builder = new AlertDialog.Builder(Location_update.this);

// 2. Chain together various setter methods to set the dialog characteristics
                            builder.setMessage("Please double check your GPS settings")
                                    .setTitle("GPS Turned Off");

// 3. Get the AlertDialog from create()
                            builder.setNeutralButton("Turn GPS On", new DialogInterface.OnClickListener() {
                                        //
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));}
//
                                    }
                            );
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                //
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }});
                            AlertDialog dialog = builder.create();
                            dialog.show();

                        }
                    }

                    else{     AlertDialog.Builder builder = new AlertDialog.Builder(Location_update.this);

// 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage("Please double check your network settings")
                                .setTitle("Cannot Detect Internet");

// 3. Get the AlertDialog from create()
                        builder.setNeutralButton("View Network Settings", new DialogInterface.OnClickListener() {
                                    //
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));}
//
                                }
                        );
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            //
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }});
                        AlertDialog dialog = builder.create();
                        dialog.show();  }}
                    else{     AlertDialog.Builder builder = new AlertDialog.Builder(Location_update.this);

// 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage("Please double check your network settings")
                                .setTitle("Cannot Detect Internet");

// 3. Get the AlertDialog from create()
                        builder.setNeutralButton("View Network Settings", new DialogInterface.OnClickListener() {
                                    //
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));}
//
                                }
                        );
                        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            //
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }});
                        AlertDialog dialog = builder.create();
                        dialog.show();  }


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }}}






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
            Location_update.this.startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_location) {

        } else if (id == R.id.nav_settings) {
            Intent myIntent = new Intent(this, Sound_settings.class);
            Location_update.this.startActivity(myIntent);

        } else if (id == R.id.nav_email) {

        } else if (id == R.id.nav_instructions) {
            Intent myIntent = new Intent(this, Instruction.class);
            Location_update.this.startActivity(myIntent);
        } else if (id == R.id.nav_tips) {
            Intent myIntent = new Intent(this, Tips.class);
            Location_update.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void stopTimer(){
        if(timer != null){
            timer.cancel();
            timer.purge();
        }
    }

    //To start timer
    private void startTimer(){
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run(){
                        requestpermisson();
                    }
                });
            }
        };
        timer.schedule(timerTask, 30000, 30000);
    }

    public void requestpermisson(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("+16173011913", null, "", null, null);
                smsManager.sendTextMessage("+16173011913", null, "", null, null);
                smsManager.sendTextMessage("+16173011913", null, "", null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
            }
        }else{         //already has permission granted
            SmsManager smsManager = SmsManager.getDefault();
            LocationManager locationManager2 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location2 = locationManager2.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Geocoder geocoder = new Geocoder(getBaseContext());
            List<Address> addresses = geocoder.getFromLocation(location2.getLatitude(), location2.getLongitude(), 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "," + obj.getAdminArea();
            smsManager.sendTextMessage("+16173011913", null, add, null, null);
            smsManager.sendTextMessage("+16173011913", null, add, null, null);
            smsManager.sendTextMessage("+16173011913", null, add, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();

        }
    }



}
