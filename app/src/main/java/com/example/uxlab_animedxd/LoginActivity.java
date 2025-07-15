package com.example.uxlab_animedxd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView feedbackText1;
    private TextView feedbackText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

//        bagian template default
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        sambungin ke object di view
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        feedbackText1 = findViewById(R.id.feedbackText1);
        feedbackText2 = findViewById(R.id.feedbackText2);
        Button loginButton = findViewById(R.id.loginButton);


//        ketika button dipencet
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Hide previous error first
            feedbackText1.setVisibility(View.INVISIBLE);
            feedbackText2.setVisibility(View.INVISIBLE);

            if (username.isEmpty()) {
                feedbackText1.setText("Username must be filled in");
                feedbackText1.setVisibility(View.VISIBLE);

            } else if (password.isEmpty()) {
                feedbackText1.setText("Password must be filled in");
                feedbackText1.setVisibility(View.VISIBLE);

            } else if (username.length() > 10 || username.length() < 5) {

                feedbackText1.setText("Length of username must be");
                feedbackText2.setText("5 - 10 characters (inclusive)");

                feedbackText1.setVisibility(View.VISIBLE);
                feedbackText2.setVisibility(View.VISIBLE);



            } else {
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                prefs.edit().putString("username", username).apply();

//                buat pindah page
                Intent intent = new Intent(LoginActivity.this, HostActivity.class);
                intent.putExtra("username", username);   // lempar nama
                startActivity(intent);
                finish();
            }

        });
    }
}
