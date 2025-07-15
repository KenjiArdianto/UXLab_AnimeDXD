package com.example.uxlab_animedxd;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class HostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            getSharedPreferences("user_prefs", MODE_PRIVATE)
                    .edit()
                    .putString("username", username)
                    .apply();
        }
    }
}