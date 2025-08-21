package com.example.uxlab_animedxd;

import android.os.Bundle;
import android.view.View; // <-- Import View

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.uxlab_animedxd.databinding.ActivityHostBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HostActivity extends AppCompatActivity {
    private ActivityHostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            getSharedPreferences("user_prefs", MODE_PRIVATE)
                    .edit()
                    .putString("username", username)
                    .apply();
        }

        BottomNavigationView navView = binding.bottomNavigation;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);

        // --- TAMBAHKAN KODE DI BAWAH INI ---
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            // Cek ID dari halaman tujuan
            if (destination.getId() == R.id.nav_detail) {
                // Jika halaman tujuan adalah nav_detail, sembunyikan navbar
                navView.setVisibility(View.GONE);
            } else {
                // Jika bukan, tampilkan kembali navbar
                navView.setVisibility(View.VISIBLE);
            }
        });
        // --- BATAS AKHIR KODE TAMBAHAN ---
    }
}