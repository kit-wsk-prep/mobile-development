package com.honley.afternoon;

import android.content.Intent;
import android.os.Bundle;

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

        binding.home.setOnClickListener(v -> {
            startActivity(new Intent(TravelActivity.this, HomeActivity.class));
        });

        binding.account.setOnClickListener(v -> {
            startActivity(new Intent(TravelActivity.this, MainActivity.class));
        });

    }
}
