package com.example.uxlab_animedxd.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uxlab_animedxd.R;
// Hapus import yang tidak perlu, tambahkan yang baru
import com.google.android.material.button.MaterialButtonToggleGroup;

public class HomeFragment extends Fragment {

    private ViewPager2 viewPager;
    private MaterialButtonToggleGroup toggleGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String user = requireContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getString("username", "User");
        ((TextView) view.findViewById(R.id.tvWelcome)).setText("Welcome, " + user + "!");

        viewPager = view.findViewById(R.id.home_view_pager);
        toggleGroup = view.findViewById(R.id.toggleGroup);

        HomePagerAdapter adapter = new HomePagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        viewPager.setUserInputEnabled(false);

        toggleGroup.check(R.id.btnNews); // Default check
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) return;
            if (checkedId == R.id.btnNews) {
                viewPager.setCurrentItem(0, true);
            } else if (checkedId == R.id.btnMangas) {
                viewPager.setCurrentItem(1, true);
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    toggleGroup.check(R.id.btnNews);
                } else {
                    toggleGroup.check(R.id.btnMangas);
                }
            }
        });
    }

    // --- Adapter untuk ViewPager ---
    private static class HomePagerAdapter extends FragmentStateAdapter {
        public HomePagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 1) {
                return new MangaFragment();
            }
            return new NewsFragment();
        }

        @Override
        public int getItemCount() {
            return 2; // Kita punya dua tab: News dan Manga
        }
    }
}