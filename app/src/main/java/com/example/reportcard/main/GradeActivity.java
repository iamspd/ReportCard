package com.example.reportcard.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reportcard.R;

public class GradeActivity extends AppCompatActivity {

    // Widgets
    private TextView gradeTitle;
    private ListView gradeList;

    // Variables
    private SharedPreferences spGrades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        spGrades = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = spGrades.getString("Name", "");

        gradeTitle = findViewById(R.id.tvGradeTitle);
        gradeTitle.setText(userName + "'s " + "Grades");
    }
}