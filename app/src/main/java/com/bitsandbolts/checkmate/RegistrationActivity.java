package com.bitsandbolts.checkmate;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bitsandbolts.checkmate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    EditText userEmail, userPassword;
    Button registerButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //This will get the Instance of Current  Auth state
        mAuth = FirebaseAuth.getInstance();

        userEmail = (EditText) findViewById(R.id.userEmail_editText);
        userPassword = (EditText) findViewById(R.id.userPassword_editText);
        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = userEmail.getText().toString();
                String Password = userPassword.getText().toString();

                registerUser(Email, Password);
            }
        });
    }

    private void registerUser(String email, String password) {

        //This method will create new User on firebase console...
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    // SignUp in success, Direct to the dashboard Page...
                    Toast.makeText(RegistrationActivity.this, "Authentication Successfully completed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(RegistrationActivity.this, "Authentication failed User already exits",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}