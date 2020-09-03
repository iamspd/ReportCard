
package com.example.reportcard.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reportcard.MainActivity;
import com.example.reportcard.R;
import com.example.reportcard.model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {

    // widgets
    /**
     * {@link EditText} takes the input from the user
     * loginUsername name of the user
     * loginPassword password of the user
     */
    private EditText loginUsername, loginPassword;

    // Variables
    private SharedPreferences spLogin;
    private Realm realmLogin;
    private static final String CLASS_NAME = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        realmLogin = Realm.getDefaultInstance();
        spLogin = PreferenceManager.getDefaultSharedPreferences(this);

        if (spLogin != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    public void loginBtnClick(View view) {
        loginUsername = findViewById(R.id.etLoginUsername);
        String userName = loginUsername.getText().toString();

        loginPassword = findViewById(R.id.etLoginPassword);
        String userPassword = loginPassword.getText().toString();

        RealmResults<Student> checkLogin = realmLogin.where(Student.class)
                .equalTo("sUsername", userName)
                .equalTo("sPassword", userPassword)
                .findAll();

        if (checkLogin.isValid()) {
            SharedPreferences.Editor editor = spLogin.edit();
            editor.putString("Username", userName);
            editor.putString("Password", userPassword);
            editor.apply();

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Please Try Again! Incorrect " +
                    "username or password. ", Toast.LENGTH_LONG).show();
            Log.e(CLASS_NAME, checkLogin.toString());
        }


    }

    public void txtSignUpClick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}