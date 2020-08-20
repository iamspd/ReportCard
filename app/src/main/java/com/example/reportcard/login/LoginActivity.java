
package com.example.reportcard.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.reportcard.R;

public class LoginActivity extends AppCompatActivity {

    // widgets
    /** {@link EditText} takes the input from the user
     * loginUsername name of the user
     * loginPassword password of the user*/
    private EditText loginUsername, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginBtnClick(View view){
        loginUsername = findViewById(R.id.etLoginUsername);
        String userName = loginUsername.getText().toString();

        loginPassword = findViewById(R.id.etLoginPassword);
        String userPassword = loginPassword.getText().toString();


    }

    public void txtSignUpClick(View view){
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}