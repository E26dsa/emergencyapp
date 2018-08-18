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

import static android.support.constraint.Constraints.TAG;

public class Preferences extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.layout);
        EditTextPreference phone1 = (EditTextPreference) findPreference("1");
        phone1.setOnPreferenceChangeListener(            new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                Log.i("onPreferenceChange", "NumberPicker Changed");
                Toast.makeText(getBaseContext(), "CHANGEEEED !!!",
                        Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("phone_numbers", MODE_PRIVATE).edit();
                EditTextPreference phone1 = (EditTextPreference)findPreference("1");
                String number_1=phone1.getText();
                editor.putString("1", number_1); // Storing strin
                editor.commit();
                return true;
            }
        });

        EditTextPreference phone2 = (EditTextPreference) findPreference("2");
        SharedPreferences editor = getSharedPreferences("phone_numbers", MODE_PRIVATE);
        phone2.setText(editor.getString("1",null));
        phone2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                EditTextPreference phone2 = (EditTextPreference) findPreference("2");
                SharedPreferences editor = getSharedPreferences("phone_numbers", MODE_PRIVATE);
                phone2.setText(editor.getString("1",null));
                return true;
            }
        });
        phone2.setOnPreferenceChangeListener(            new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference,
                                              Object newValue) {
                Log.i("onPreferenceChange", "NumberPicker Changed");
                Toast.makeText(getBaseContext(), "CHANGEEEED !!!",
                        Toast.LENGTH_SHORT).show();
                SharedPreferences editor = getSharedPreferences("phone_numbers", MODE_PRIVATE);
                EditTextPreference phone2 = (EditTextPreference) findPreference("2");
                phone2.setText(editor.getString("1",null));
                return true;
            }
        });
    }}
