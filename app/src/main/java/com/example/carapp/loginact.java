package com.example.carapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginact extends AppCompatActivity {
    Button btn;
    Button button;
    EditText  inputEmail, inputPassword;
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
        btn = findViewById(R.id.log);
        button =findViewById(R.id.regis);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforlogin();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginact.this, registrationact.class);
                startActivity(intent);
            }
        });

    }

    private void perforlogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (!email.matches(emailpattern)) {
            inputEmail.setError("Please enter correct email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("PLease enter your correct password");

        } else {
            progressDialog.setMessage("please wait while login.....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        progressDialog.dismiss();
                        SendUserToNextActivity();
                        Toast.makeText(loginact.this, "login successfull", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(loginact.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void SendUserToNextActivity() {
        Intent intent = new Intent(loginact.this,istpage.class);
        startActivity(intent);
    }
}