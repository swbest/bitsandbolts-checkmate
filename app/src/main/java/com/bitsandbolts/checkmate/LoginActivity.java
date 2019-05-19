package com.bitsandbolts.checkmate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText userEmail, userPassword;
    Button loginButton, newUserButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //This will get the Instance of Current  Auth state
        mAuth = FirebaseAuth.getInstance();

        userEmail = (EditText) findViewById(R.id.Email_editText);
        userPassword = (EditText) findViewById(R.id.Password_editText);
        loginButton = (Button) findViewById(R.id.Login_button);
        newUserButton = (Button) findViewById(R.id.NewUser_Button);

        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = userEmail.getText().toString();
                String Password = userPassword.getText().toString();

                loginUser(Email,Password);
            }
        });

    }

    private void loginUser(String email, String password) {
        //This method will authenticate the user details..
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    // Login in success, Direct to Dashboard page

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                    startActivity(intent);


                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(LoginActivity.this, "Login failed: Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}