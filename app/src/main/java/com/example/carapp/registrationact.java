package com.example.carapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registrationact extends AppCompatActivity {
    TextView alReadyHaveAccount;
EditText inputName,inputLastname,inputEmail,inputPassword,inputConfirmPassword;
Button btn;
String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
ProgressDialog progressDialog;
FirebaseAuth mAuth;
FirebaseUser muser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationact);
        inputName = findViewById(R.id.firstname);
        inputLastname = findViewById(R.id.lastname);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        inputConfirmPassword = findViewById(R.id.cpassword);
        progressDialog = new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        btn = findViewById(R.id.signup);
        alReadyHaveAccount = findViewById(R.id.alreadyAcount);
        alReadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(registrationact.this,loginact.class);
                startActivity(i);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforAuth();
                
            }
        });
    }

    private void perforAuth() {
        String firstname = inputName.getText().toString();
        String lastname = inputLastname.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String confirmpassword = inputConfirmPassword.getText().toString();

        if (!email.matches(emailpattern))
        {
            inputEmail.setError("Please enter correct email");
        } else if (password.isEmpty()|| password.length()<6) {
            inputPassword.setError("PLease enter your correct password");
            
        } else if (!password.equals(confirmpassword)) {
            inputConfirmPassword.setError("Password does not match");
            
        }else {
            progressDialog.setMessage("please wait while reistration.....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();



            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        SendUserToNextActivity();
                        Toast.makeText(registrationact.this, "register successfull", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(registrationact.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void SendUserToNextActivity() {
        Intent intent = new Intent(registrationact.this,loginact.class);
        startActivity(intent);
    }
}