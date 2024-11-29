package com.honley.afternoon;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.honley.afternoon.databinding.ActivityHomeBinding;
import com.honley.afternoon.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity {
    private ActivityTravelBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTravelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String emailPreferences = sharedPreferences.getString("email", null);
        String passwordPreferences = sharedPreferences.getString("password", null);

        if (emailPreferences.isEmpty() && passwordPreferences.isEmpty()) {
            binding.account.setOnClickListener(v -> {
                startActivity(new Intent(TravelActivity.this, MainActivity.class));
            });
        } else {
            binding.account.setOnClickListener(v -> {
                startActivity(new Intent(TravelActivity.this, AccountActivity.class));
            });
        }

        binding.home.setOnClickListener(v -> {
            startActivity(new Intent(TravelActivity.this, HomeActivity.class));
        });

        binding.arcdetriophe.setOnClickListener(v -> {
            String place = binding.arcdetriophe.toString();
            dialog(place);
            System.out.println(place);
        });
    }

    private void dialog(String place) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.place);
        dialog.setCancelable(true);

        dialog.show();
    }
}
