package com.example.reportcard.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reportcard.R;
import com.example.reportcard.model.Subject;

import java.util.ArrayList;
import java.util.Arrays;

public class GradeActivity extends AppCompatActivity {

    // Widgets
    private TextView gradeTitle;
    private ListView subjectList;

    // Variables
    private SharedPreferences spGrades;
    private ArrayList<Subject> arrayListSubjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        spGrades = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = spGrades.getString("Name", "");

        gradeTitle = findViewById(R.id.tvGradeTitle);
        gradeTitle.setText(userName + "'s " + "Grades");

        arrayListSubjects = new ArrayList<>(Arrays.asList(
                new Subject("COMP 228", "A"),
                new Subject("COMP 311", "A+"),
                new Subject("COMP 229", "B"),
                new Subject("COMM 171", "C")
        ));

        SubjectAdapter subjectAdapter = new SubjectAdapter(this, arrayListSubjects);

        subjectList = findViewById(R.id.lvSubjectGrades);
        subjectList.setAdapter(subjectAdapter);

    }
}