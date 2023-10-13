package com.example.bookmarkingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString().trim();
                String enteredPassword = passwordEditText.getText().toString().trim();

                // Log the entered username and password
                Log.d("LoginDebug", "Entered Username: " + enteredUsername);
                Log.d("LoginDebug", "Entered Password: " + enteredPassword);

                ArrayList<User> users = User.readUsersFromCSV(Login.this);

                for (User user : users) {
                    Log.d("LoginDebug", "CSV Username: " + user.getUsername());
                    Log.d("LoginDebug", "CSV Password: " + user.getPassword());
                }

//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);


                for (User user : users) {
                    if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                        // Successful login
                        // 1. Create an instance of the User class
                        // 2. Create an instance of an intent to go to the MainActivity
                        Intent intent = new Intent(Login.this, MainActivity.class);

                        // 3. Put your instance of the user class into the intent
                        intent.putExtra("USER", user);

                        // 4. Start the MainActivity
                        startActivity(intent);
                        finish();
                        return;
                    }
                }

                // If we reach here, it means login failed
                Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}