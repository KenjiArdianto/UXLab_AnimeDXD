package com.example.uxlab_animedxd.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.weebzone.WeebZoneActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    /* ---------- banner data ---------- */
    private final List<SlideItem> real = Arrays.asList(
            new SlideItem(R.drawable.banner1, "RENT‑A‑GIRLFRIEND S4\nJULY 2025"),
            new SlideItem(R.drawable.banner2, "FRAGRANT FLOWER BLOOMS\nJULY 2025"),
            new SlideItem(R.drawable.banner3, "ANOTHER ANIME\nCOMING SOON")
    );
    private final List<SlideItem> loop = new ArrayList<>();

    private ViewPager2 pager;
    private Handler  handler;
    private Runnable auto;
    private static final int PERIOD = 5_000;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        return inf.inflate(R.layout.fragment_home, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle s) {

        /* ---------- greeting ---------- */
        String user = requireContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getString("username", "User");
        ((TextView) v.findViewById(R.id.tvWelcome))
                .setText("Welcome, " + user + "!");

        /* ---------- toggle group ---------- */
        MaterialButtonToggleGroup group = v.findViewById(R.id.toggleGroup);
        MaterialButton btnNews   = v.findViewById(R.id.btnNews);
        MaterialButton btnMangas = v.findViewById(R.id.btnMangas);

        // default: News checked (ditetapkan di XML atau di sini)
        group.check(R.id.btnNews);

        group.addOnButtonCheckedListener((g, checkedId, isChecked) -> {
            if (!isChecked) return;              // abaikan event uncheck
            if (checkedId == R.id.btnMangas) {   // pindah ke Manga page
                startActivity(new Intent(requireContext(), WeebZoneActivity.class));
                requireActivity().overridePendingTransition(0, 0);   // ★ no animation
            }
        });

        /* ---------- banner ---------- */
        buildLoopList();
        pager = v.findViewById(R.id.viewPager);
        pager.setAdapter(new SliderAdapter(loop));
        pager.setCurrentItem(1, false);

        setupDots(v);
        setupAutoSlide();
    }

    /* kembali ke News tab & lanjut auto‑slide */
    @Override public void onResume() {
        super.onResume();
        MaterialButtonToggleGroup g = requireView().findViewById(R.id.toggleGroup);
        g.check(R.id.btnNews);                     // force News
        handler.postDelayed(auto, PERIOD);
    }
    @Override public void onPause()  { super.onPause(); handler.removeCallbacks(auto); }

    /* ---------- helpers ---------- */
    private void buildLoopList() {
        loop.clear();
        loop.add(real.get(real.size() - 1));  // dummy first
        loop.addAll(real);
        loop.add(real.get(0));                // dummy last
    }

    private void setupDots(View v) {
        LinearLayout dotLL = v.findViewById(R.id.indicatorDots);
        ImageView[] dots   = new ImageView[real.size()];
        int act = R.drawable.dot_active, ina = R.drawable.dot_inactive;

        for (int i = 0; i < real.size(); i++) {
            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(),
                    i == 0 ? act : ina));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 8, 0);
            dotLL.addView(dots[i], lp);
        }

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int pos) {
                int idx = (pos - 1 + real.size()) % real.size();
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(
                            requireContext(), i == idx ? act : ina));
                }
                handler.removeCallbacks(auto);
                handler.postDelayed(auto, PERIOD);
            }
            @Override public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    int p = pager.getCurrentItem();
                    if (p == 0)                pager.setCurrentItem(real.size(), false);
                    else if (p == loop.size()-1) pager.setCurrentItem(1, false);
                }
            }
        });
    }

    private void setupAutoSlide() {
        handler = new Handler(Looper.getMainLooper());
        auto = () -> {
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            handler.postDelayed(auto, PERIOD);
        };
    }
}
