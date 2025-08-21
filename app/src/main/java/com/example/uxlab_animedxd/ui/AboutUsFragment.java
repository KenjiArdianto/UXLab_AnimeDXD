package com.example.uxlab_animedxd.ui;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.example.uxlab_animedxd.LoginActivity;
import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.databinding.FragmentAboutUsBinding;
import com.example.uxlab_animedxd.databinding.FragmentItemListBinding;

public class AboutUsFragment extends Fragment {

    private TextView welcomeText;
    private FragmentAboutUsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            View customActionBarView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.custom_actionbar, null);
            actionBar.setCustomView(customActionBarView);

            Toolbar parent = (Toolbar) customActionBarView.getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }

        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.top_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_logout) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                    return true;
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        // Load username from SharedPreferences
        SharedPreferences prefs = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = prefs.getString("username", "");

        welcomeText = binding.welcomeText;
        String welcomeMessage = "Welcome, " + username + "!";
        welcomeText.setText(welcomeMessage);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentAboutUsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
