package com.example.reportcard.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reportcard.R;
import com.example.reportcard.model.Student;

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {

    // Variables
    /**
     * dialog box displayed on the click of the {@link Button} regBtnRegister
     */
    private Dialog regDialog;
    private SharedPreferences spRegistration;
    private Realm realmRegistration;

    /**
     * class name to be used for different purposes
     * such as logging the operations of the application
     */
    public static final String CLASS_NAME = RegisterActivity.class.getSimpleName();

    // widgets

    /**
     * bind UI input to fetch the input given by the user using {@link EditText}
     * regName for fetching the name of the user
     * regEmail for fetching the Email of the user
     */
    private EditText regFirstName, regLastName, regEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        realmRegistration = Realm.getDefaultInstance();
    }

    /**
     * method handles the click events of the
     * {@link Button} regBtnRegister
     */
    public void regBtnClick(View view) {

        regFirstName = findViewById(R.id.etFirstName);
        final String firstName = regFirstName.getText().toString();

        regFirstName = findViewById(R.id.etLastName);
        final String lastName = regLastName.getText().toString();

        regEmail = findViewById(R.id.etEmail);
        final String userEmail = regEmail.getText().toString();

        // Widgets

        /** It is allowing the popup to get closed without affecting the UI */
        TextView popClose;

        /** Popup buttons for operating it on certain inputs
         * popBtnCancel clears the UI and closes the popup
         * popBtnSubmit sends the Email to the Admin of user details*/
        Button popBtnCancel, popBtnSubmit;

        // assigning the dialog box to the custom layout custom_popup.xml

        regDialog.setContentView(R.layout.custom_popup);

        // assigning the appropriate widgets {@link Button, TextView} to the layout components

        popClose = findViewById(R.id.txtPopClose);
        popBtnCancel = findViewById(R.id.btnPopCancel);
        popBtnSubmit = findViewById(R.id.btnPopSubmit);

        /** Click event of the {@link TextView} popClose dismisses the
         * dialog box from the UI focus */
        popClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regDialog.dismiss();
            }
        });

        /** Click event of the {@link Button} popBtnCancel dismisses the dialog box
         * and clears the given inputs in the {@link EditText} regName & regEmail */
        popBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regFirstName.setText("");
                regLastName.setText("");
                regEmail.setText("");
                regDialog.dismiss();
            }
        });

        /** Click event of the {@link Button} popBtnSubmit sends an intent to the mail
         * app with the given subject and body text */
        popBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fNameFirst = Character.toString(firstName.charAt(0));
                String fNameSecond = Character.toString(firstName.charAt(1));

                String lNameFirst = Character.toString(lastName.charAt(0));
                String lNameSecond = Character.toString(lastName.charAt(1));

                final String userName = fNameFirst + fNameSecond
                        + lNameFirst + lNameSecond;

                realmRegistration.executeTransactionAsync(new Realm.Transaction() {
                                                              @Override
                                                              public void execute(Realm realm) {

                                                                  Student studentObject = realm.createObject(Student.class);
                                                                  studentObject.setsFirstName(firstName);
                                                                  studentObject.setsLastName(lastName);
                                                                  studentObject.setsEmail(userEmail);
                                                                  studentObject.setsUsername(userName);
                                                              }
                                                          }, new Realm.Transaction.OnSuccess() {
                                                              @Override
                                                              public void onSuccess() {
                                                                  Toast.makeText(RegisterActivity.this, "User Added.",
                                                                          Toast.LENGTH_LONG).show();

                                                                  spRegistration = PreferenceManager.getDefaultSharedPreferences(RegisterActivity.this);
                                                                  SharedPreferences.Editor editor = spRegistration.edit();
                                                                  editor.putString("Username", userName);
                                                                  editor.putString("Password", userName);
                                                                  editor.apply();

                                                                  Intent regEmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                                                          "mailto", "abc@gmail.com", null));
                                                                  regEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registration for Access");
                                                                  regEmailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, Please consider my " +
                                                                          "details given below, and verify them. " +
                                                                          "Name: " + firstName + ", " + "\n" +
                                                                          "Name: " + lastName + ", " + "\n" +
                                                                          "Email: " + userEmail + ". " + "\n \n" +
                                                                          "Thanks & Regards " + "\n " + firstName);

                                                                  /** {@link PackageManager} checks whether the requested application is
                                                                   * there in the system or not  */
                                                                  if (RegisterActivity.this.getPackageManager() != null) {
                                                                      startActivity(Intent.createChooser(regEmailIntent, "Send email..."));
                                                                  }
                                                              }
                                                          }, new Realm.Transaction.OnError() {
                                                              @Override
                                                              public void onError(Throwable error) {
                                                                  Log.e(CLASS_NAME, error.getMessage());
                                                              }
                                                          }
                );
            }
        });

        regDialog.show();
    }

    public void txtSignInClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}