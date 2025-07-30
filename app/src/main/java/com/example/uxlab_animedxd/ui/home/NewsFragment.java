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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.example.uxlab_animedxd.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsFragment extends Fragment {
    private final List<SlideItem> real = Arrays.asList(
            new SlideItem(R.drawable.banner1, "RENT‑A‑GIRLFRIEND S4\nJULY 2025"),
            new SlideItem(R.drawable.banner2, "FRAGRANT FLOWER BLOOMS\nJULY 2025"),
            new SlideItem(R.drawable.banner3, "ANOTHER ANIME\nCOMING SOON")
    );
    private final List<SlideItem> loop = new ArrayList<>();
    private ViewPager2 pager;
    private Handler handler;
    private Runnable auto;
    private static final int PERIOD = 5_000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buildLoopList();
        pager = view.findViewById(R.id.viewPager);
        pager.setAdapter(new SliderAdapter(loop));
        pager.setCurrentItem(1, false);
        setupDots(view);
        setupAutoSlide();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (handler != null && auto != null) {
            handler.postDelayed(auto, PERIOD);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (handler != null) {
            handler.removeCallbacks(auto);
        }
    }
    private void buildLoopList() {
        loop.clear();
        loop.add(real.get(real.size() - 1));
        loop.addAll(real);
        loop.add(real.get(0));
    }

    private void setupDots(View v) {
        LinearLayout dotLL = v.findViewById(R.id.indicatorDots);
        ImageView[] dots = new ImageView[real.size()];
        int act = R.drawable.dot_active, ina = R.drawable.dot_inactive;

        for (int i = 0; i < real.size(); i++) {
            dots[i] = new ImageView(requireContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), i == 0 ? act : ina));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(8, 0, 8, 0);
            dotLL.addView(dots[i], lp);
        }

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int pos) {
                int idx = (pos - 1 + real.size()) % real.size();
                for (int i = 0; i < dots.length; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(requireContext(), i == idx ? act : ina));
                }
                if(handler != null) {
                    handler.removeCallbacks(auto);
                    handler.postDelayed(auto, PERIOD);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    int p = pager.getCurrentItem();
                    if (p == 0) pager.setCurrentItem(real.size(), false);
                    else if (p == loop.size() - 1) pager.setCurrentItem(1, false);
                }
            }
        });
    }

    private void setupAutoSlide() {
        handler = new Handler(Looper.getMainLooper());
        auto = () -> {
            if (pager != null) {
                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
        };
    }
}