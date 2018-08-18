package com.apps.jinstin.emergencyapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.support.constraint.Constraints.TAG;

public class Preferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.layout);
        EditTextPreference phone1 = (EditTextPreference) findPreference("1");
        phone1.setOnPreferenceChangeListener(
          new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                EditTextPreference phone1 = (EditTextPreference)findPreference("1");
                String p1=phone1.getText();
                Pattern testPattern= Pattern.compile("^[1-9][0-9]{9,11}$");
                Matcher teststring= testPattern.matcher(p1);
                if(!teststring.matches())
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Preferences.this).create();
                    alertDialog.setTitle("Invalid phone number");
                    alertDialog.setMessage("Please ensure that the country code (no leading zeroes) is included and try again");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else{phone1.setSummary("+"+p1);Toast.makeText(Preferences.this, "Contact 1's phone number saved", Toast.LENGTH_LONG).show();}
                SharedPreferences.Editor editor = getSharedPreferences("phone_numbers", MODE_PRIVATE).edit();
                editor.putString("1", "+"+p1); // Storing strin
                editor.apply();
                return true;
            }
        });
        SharedPreferences sp = getSharedPreferences("phone_numbers", MODE_PRIVATE);
        String p1=sp.getString("1","Please enter a phone number");
        Pattern testPattern= Pattern.compile("^[1-9][0-9]{9,11}$");
        Matcher teststring= testPattern.matcher(p1);
        if(!teststring.matches())
        {
            p1="Please enter a phone number";
        }
        phone1.setSummary("+"+p1);

        EditTextPreference phone2 = (EditTextPreference) findPreference("2");
        phone2.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {

                    @Override
                    public boolean onPreferenceChange(Preference preference,
                                                      Object newValue) {
                        EditTextPreference phone2 = (EditTextPreference)findPreference("2");
                        String p2=phone2.getText();
                        Pattern testPattern= Pattern.compile("^[1-9][0-9]{9,11}$");
                        Matcher teststring= testPattern.matcher(p2);
                        if(!teststring.matches())
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(Preferences.this).create();
                            alertDialog.setTitle("Invalid phone number");
                            alertDialog.setMessage("Please ensure that the country code (no leading zeroes) is included and try again");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        else{phone2.setSummary("+"+p2);Toast.makeText(Preferences.this, "Contact 2's phone number saved", Toast.LENGTH_LONG).show();}
                        SharedPreferences.Editor editor = getSharedPreferences("phone_numbers", MODE_PRIVATE).edit();
                        editor.putString("2", "+"+p2); // Storing strin
                        editor.apply();
                        return true;
                    }
                });
        SharedPreferences sp2 = getSharedPreferences("phone_numbers", MODE_PRIVATE);
        String p2=sp2.getString("2","Please enter a phone number");
        Pattern testPattern2= Pattern.compile("^[1-9][0-9]{9,11}$");
        Matcher teststring2= testPattern.matcher(p2);
        if(!teststring2.matches())
        {
            p2="Please enter a phone number";
        }
        phone2.setSummary("+"+p2);

        EditTextPreference phone3 = (EditTextPreference) findPreference("3");
        phone3.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {

                    @Override
                    public boolean onPreferenceChange(Preference preference,
                                                      Object newValue) {
                        EditTextPreference phone3 = (EditTextPreference)findPreference("3");
                        String p3=phone3.getText();
                        Pattern testPattern3= Pattern.compile("^[1-9][0-9]{9,11}$");
                        Matcher teststring3= testPattern3.matcher(p3);
                        if(!teststring3.matches())
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(Preferences.this).create();
                            alertDialog.setTitle("Invalid phone number");
                            alertDialog.setMessage("Please ensure that the country code (no leading zeroes) is included and try again");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        else{phone3.setSummary("+"+p3);Toast.makeText(Preferences.this, "Contact 3's phone number saved", Toast.LENGTH_LONG).show();}
                        SharedPreferences.Editor editor = getSharedPreferences("phone_numbers", MODE_PRIVATE).edit();
                        editor.putString("3", "+"+p3); // Storing strin
                        editor.apply();
                        return true;
                    }
                });
        SharedPreferences sp3 = getSharedPreferences("phone_numbers", MODE_PRIVATE);
        String p3=sp3.getString("3","Please enter a phone number");
        Pattern testPattern3= Pattern.compile("^[1-9][0-9]{9,11}$");
        Matcher teststring3= testPattern.matcher(p3);
        if(!teststring3.matches())
        {
            p3="Please enter a phone number";
        }
        phone3.setSummary("+"+p3);




    }}
