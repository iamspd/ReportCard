package com.example.reportcard.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.reportcard.ProfileActivity;
import com.example.reportcard.R;
import com.example.reportcard.login.LoginActivity;

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

    public void profileBtnClick(View view){
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void gradeBtnClick(View view){
        startActivity(new Intent(this, GradeActivity.class));
    }

    public void assistanceBtnClick(View view){
        String url = "https://www.centennialcollege.ca/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));

        PackageManager packageManager = this.getPackageManager();
        if (i.resolveActivity(packageManager) != null) {
            startActivity(i);
        }
    }

    public void logOutBtnClick(View view){
        SharedPreferences.Editor editor = spMain.edit();
        editor.putString("Name", "");
        editor.putString("Username", "");
        editor.putString("Password", "");
        editor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}