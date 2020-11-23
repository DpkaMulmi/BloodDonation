package com.example.blooddonationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    //Variables
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hooks yo all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPassword = findViewById(R.id.password);
        regPhoneNo = findViewById(R.id.phoneNo);
        regBtn = findViewById(R.id.login_btn);
        regToLoginBtn = findViewById(R.id.signup_screen);


        //Save data in FireBase on button click
    regBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            //Get all the values
            String name = regName.getEditText().getText().toString();
            String username = regUsername.getEditText().getText().toString();
            String email = regEmail.getEditText().getText().toString();
            String password = regPassword.getEditText().getText().toString();
            String phoneno = regPhoneNo.getEditText().getText().toString();
            UserHelpClass helperClass = new UserHelpClass(name, username, email, phoneno, password);
            reference.child(phoneno).setValue(helperClass);
        }
    });
    }


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=15){
            regUsername.setError("Username too long");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            regUsername.setError("White Spaces are not allowed");
            return false;
        }
        else{
            regUsername.setError(null);
            return true;
        }
    }

    private Boolean validateEmail()  {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid email address");
            return false;}
        else{
            regEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        }
        else{
            regPhoneNo.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])"+       //at least 1 digit
               //"(?=.*[a-z])"+       //at least 1 lower case letter
              //"(?=.*[A-Z])"+      //at least 1 upper case letter
              "(?=.*[a-zA-Z])"+        //any letter
            "(?=.*[@#$%^&+=])"+        //atleast 1 special character
            "(?=\\s+$)"+              //no white space
        ".{4,}"+                      //at least 4 characters
        "$";


        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordVal)){
            regEmail.setError("Password is too weak");
            return false;}
        else{
            regPassword.setError(null);
            return true;
        }
    }




    //Save data in FireBase on button click
    public void registerUser (View view) {

        if (!validateName() | !validatePassword() | !validateUsername() | !validateEmail() | !validatePhoneNo());
            return;

            String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String phoneno = regPhoneNo.getEditText().getText().toString();
        UserHelpClass helperClass = new UserHelpClass(name, username, email, phoneno, password);
        reference.child(phoneno).setValue(helperClass);
    }
}