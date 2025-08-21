package com.example.uxlab_animedxd.ui.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.uxlab_animedxd.LoginActivity;
import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.data.DummyAnimeDataSource;
import com.example.uxlab_animedxd.databinding.FragmentItemListBinding;
import com.example.uxlab_animedxd.model.Anime;

import java.util.ArrayList;

public class ItemListFragment extends Fragment {

    private FragmentItemListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupToolbar();
        setupRecyclerView();
        setupMenu();
    }

    private void setupToolbar() {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            @SuppressLint("InflateParams") View customActionBarView = LayoutInflater.from(requireContext())
                    .inflate(R.layout.custom_actionbar, null);
            actionBar.setCustomView(customActionBarView);

            Toolbar parent = (Toolbar) customActionBarView.getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }

    }

    private void setupRecyclerView() {
        ArrayList<Anime> animeList = DummyAnimeDataSource.getAnimeList(requireContext());
        binding.animeList.setLayoutManager(new LinearLayoutManager(getContext()));
        AnimeAdapter adapter = new AnimeAdapter(animeList);
        binding.animeList.setAdapter(adapter);

        adapter.setOnItemClickListener(anime -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("animeItem", anime);

            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_listFragment_to_detailFragment, bundle);
        });
    }

    private void setupMenu() {
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}