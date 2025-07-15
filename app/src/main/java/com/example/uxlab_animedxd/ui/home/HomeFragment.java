package com.example.uxlab_animedxd.ui.home;

import android.content.Context;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    /* ------------ data asli ------------ */
    private final List<SlideItem> real = Arrays.asList(
            new SlideItem(R.drawable.banner1, "RENT‑A‑GIRLFRIEND S4\nJULY 2025"),
            new SlideItem(R.drawable.banner2, "FRAGRANT FLOWER BLOOMS\nJULY 2025"),
            new SlideItem(R.drawable.banner3, "ANOTHER ANIME\nCOMING SOON")
    );

    /* list dengan dummy di awal & akhir */
    private final List<SlideItem> loop = new ArrayList<>();

    private ViewPager2 pager;
    private Handler handler;
    private Runnable auto;
    private final int PERIOD = 5000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup ct,
                             @Nullable Bundle st) {
        return inf.inflate(R.layout.fragment_home, ct, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle s) {

        /* ---------- username ---------- */
        String user = requireContext()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .getString("username", "User");
        ((TextView) v.findViewById(R.id.tvWelcome)).setText("Welcome, " + user + "!");

        /* ---------- siapkan list loop ---------- */
        loop.clear();
        loop.add(real.get(real.size() - 1));   // dummy (last) → awal
        loop.addAll(real);                     // data asli
        loop.add(real.get(0));                 // dummy (first) → akhir

        /* ---------- ViewPager2 ---------- */
        pager = v.findViewById(R.id.viewPager);
        pager.setAdapter(new SliderAdapter(loop));
        pager.setCurrentItem(1, false);        // index 1 = banner1 asli

        /* ---------- dot indikator ---------- */
        LinearLayout dots = v.findViewById(R.id.indicatorDots);
        ImageView[] dot = new ImageView[real.size()];
        int act = R.drawable.dot_active;
        int ina = R.drawable.dot_inactive;

        for (int i = 0; i < real.size(); i++) {
            dot[i] = new ImageView(requireContext());
            dot[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), i == 0 ? act : ina));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 8, 0);
            dots.addView(dot[i], lp);
        }

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int pos) {
                int realPos = (pos - 1 + real.size()) % real.size();   // map 1..N -> 0..N-1
                for (int i = 0; i < dot.length; i++) {
                    dot[i].setImageDrawable(ContextCompat.getDrawable(
                            requireContext(), i == realPos ? act : ina));
                }
                handler.removeCallbacks(auto);
                handler.postDelayed(auto, PERIOD);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    int pos = pager.getCurrentItem();
                    if (pos == 0)                       // dummy pertama → lompat ke last real
                        pager.setCurrentItem(real.size(), false);
                    else if (pos == loop.size() - 1)    // dummy terakhir → lompat ke first real
                        pager.setCurrentItem(1, false);
                }
            }
        });

        /* ---------- auto‑slide ---------- */
        handler = new Handler(Looper.getMainLooper());
        auto = () -> {
            pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            handler.postDelayed(auto, PERIOD);
        };
        handler.postDelayed(auto, PERIOD);
    }

    @Override public void onPause()  { super.onPause();  handler.removeCallbacks(auto); }
    @Override public void onResume() { super.onResume(); handler.postDelayed(auto, PERIOD); }
}
