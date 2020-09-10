package com.example.reportcard.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.reportcard.R;

public class MainActivity extends AppCompatActivity {

    // Widgets
    private TextView mTitle;

    // Variables
    private SharedPreferences spMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spMain = PreferenceManager.getDefaultSharedPreferences(this);
        String studentName = spMain.getString("Name", "");

        mTitle = findViewById(R.id.txtMainTitle);
        mTitle.setText(R.string.welcome + studentName);

    }
}