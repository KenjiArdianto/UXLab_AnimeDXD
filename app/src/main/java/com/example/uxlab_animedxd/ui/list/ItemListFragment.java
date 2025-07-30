package com.example.uxlab_animedxd.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbar);
    }

    private void setupRecyclerView() {
        ArrayList<Anime> animeList = DummyAnimeDataSource.getAnimeList(requireContext());
        binding.animeList.setLayoutManager(new LinearLayoutManager(getContext()));
        AnimeAdapter adapter = new AnimeAdapter(animeList);
        binding.animeList.setAdapter(adapter);

        adapter.setOnItemClickListener(anime ->
                Toast.makeText(getContext(), "Clicked: " + anime.getTitle(), Toast.LENGTH_SHORT).show()
        );
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
                    Toast.makeText(getContext(), "Logout clicked!", Toast.LENGTH_SHORT).show();
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