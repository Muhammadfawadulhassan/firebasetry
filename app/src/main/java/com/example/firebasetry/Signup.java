package com.example.firebasetry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText txtEmail,txtPassword,textConfirmPassword;
    Button btn_register;
    ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("signup");

        txtEmail =(EditText)findViewById(R.id.email);
        txtPassword =(EditText)findViewById(R.id.password);
        textConfirmPassword =(EditText)findViewById(R.id.ConfirmPassword);
        btn_register =(Button)findViewById(R.id.sign_up_button);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String confirmPassword = textConfirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Signup.this,"Please Enter Email",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup.this,"Please Enter password ",Toast.LENGTH_LONG);
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(Signup.this,"Please Enter confirmpassword",Toast.LENGTH_LONG);
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(Signup.this,"Password too short",Toast.LENGTH_LONG);

                }
                progressBar.setVisibility(View.VISIBLE);

                if(password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup.this,"registration complete",Toast.LENGTH_LONG);
                                    } else {
                                        Toast.makeText(Signup.this,"authentaction fail",Toast.LENGTH_LONG);

                                    }

                                    // ...
                                }
                            });

                }

            }
        });
    }
}
