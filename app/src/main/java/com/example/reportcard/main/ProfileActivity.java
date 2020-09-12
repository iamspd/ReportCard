package com.example.reportcard.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reportcard.R;
import com.example.reportcard.model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProfileActivity extends AppCompatActivity {

    // Widgets
    private EditText pFirstName, pLastName, pUsername, pEmail, pPhone;
    private Button pChangePassword;

    // Variables
    private SharedPreferences spProfile;
    private Realm realmProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        realmProfile = Realm.getDefaultInstance();

        findAllViews();

        fetchData();
    }

    private void findAllViews() {
        pFirstName = findViewById(R.id.etFirstName);
        pLastName = findViewById(R.id.etLastName);
        pUsername = findViewById(R.id.etUsername);
        pEmail = findViewById(R.id.etEmail);
        pPhone = findViewById(R.id.etPhone);
        pChangePassword = findViewById(R.id.btnChangePassword);
    }

    private void fetchData() {
        Student student = new Student();
        spProfile = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = spProfile.getString("Username", "");

        RealmResults<Student> getData = realmProfile.where(Student.class)
                .equalTo("sUsername", userName)
                .findAll();

        realmProfile.beginTransaction();
        while (getData != null) {
            pFirstName.setText(student.getsFirstName());
            pLastName.setText(student.getsLastName());
            pUsername.setText(student.getsUsername());
            pEmail.setText(student.getsEmail());
            pPhone.setText(student.getsPhone());
        }
    }
}