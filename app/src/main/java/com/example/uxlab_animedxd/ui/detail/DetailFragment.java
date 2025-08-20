package com.example.uxlab_animedxd.ui.detail;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;

import com.example.uxlab_animedxd.R;
import com.example.uxlab_animedxd.model.Anime;

public class DetailFragment extends Fragment {

    private Anime currentAnime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anime_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleDetail = view.findViewById(R.id.anime_title_detail);
        ImageView imageDetail = view.findViewById(R.id.anime_image_detail);
        TextView genreDetail = view.findViewById(R.id.anime_genre_detail);
        TextView synopsisDetail = view.findViewById(R.id.anime_synopsis_detail);
        AppCompatButton backButton = view.findViewById(R.id.back_btn);
        Button reviewButton = view.findViewById(R.id.review_btn);

        if (getArguments() != null) {
            currentAnime = getArguments().getParcelable("animeItem");
        }

        if (currentAnime != null) {
            titleDetail.setText(currentAnime.getTitle());
            imageDetail.setImageResource(currentAnime.getCover());
            genreDetail.setText(currentAnime.getGenre());
            synopsisDetail.setText(currentAnime.getSynopsis());
        }

        backButton.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());

        reviewButton.setOnClickListener(v -> {
            showAddReviewDialog();
        });

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    private void showAddReviewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_review, null);
        builder.setView(dialogView);

        EditText reviewEditText = dialogView.findViewById(R.id.review_popup_edit_text);
        Button submitButton = dialogView.findViewById(R.id.submit_popup_btn);

        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        submitButton.setOnClickListener(v -> {
            String reviewText = reviewEditText.getText().toString().trim();

            if (reviewText.isEmpty()) {
                reviewEditText.setError("Review must be filled in.");
            } else {
                Toast.makeText(getContext(), "Review submitted!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}