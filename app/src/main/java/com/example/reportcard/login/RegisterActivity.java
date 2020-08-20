package com.example.reportcard.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.reportcard.R;

public class RegisterActivity extends AppCompatActivity {

    // variables
    /** dialog box displayed on the click of the {@link Button} regBtnRegister */
    private Dialog regDialog;

    /** class name to be used for different purposes
     * such as logging the operations of the application */
    public static final String CLASS_NAME = RegisterActivity.class.getSimpleName();

    // widgets

    /** bind UI input to fetch the input given by the user using {@link EditText}
     * regName for fetching the name of the user
     * regEmail for fetching the Email of the user*/
    private EditText regName, regEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    /** method handles the click events of the
     * {@link Button} regBtnRegister */
    public void regBtnClick(View view){

        regName = findViewById(R.id.etName);
        final String userName = regName.getText().toString();

        regEmail = findViewById(R.id.etEmail);
        final String userEmail = regEmail.getText().toString();

        // Widgets

        /** It is allowing the popup to be closed without affecting the UI */
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
                regName.setText("");
                regEmail.setText("");
                regDialog.dismiss();
            }
        });

        /** Click event of the {@link Button} popBtnSubmit sends an intent to the mail
         * app with the given subject and body text */
        popBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regEmailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","abc@gmail.com", null));
                regEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Registration for Access");
                regEmailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, Please consider my " +
                        "details given below, and verify them. " +
                        "Name: " + userName + ", " + "\n" +
                        "Email: " + userEmail + ". " + "\n \n" +
                        "Thanks & Regards " + "\n " +userName);

                /** {@link PackageManager} checks whether the requested application is
                 * there in the system or not  */
                if (RegisterActivity.this.getPackageManager() != null){
                    startActivity(Intent.createChooser(regEmailIntent, "Send email..."));
                }
            }
        });

        regDialog.show();
    }

    public void txtSignInClick(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}