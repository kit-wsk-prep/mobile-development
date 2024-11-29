package com.honley.afternoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.honley.afternoon.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String emailPreferences = sharedPreferences.getString("email", null);
        String passwordPreferences = sharedPreferences.getString("password", null);

        if (emailPreferences.isEmpty() && passwordPreferences.isEmpty()) {
            binding.account.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            });
        } else {
            binding.account.setOnClickListener(v -> {
                startActivity(new Intent(HomeActivity.this, AccountActivity.class));
            });
        }

        binding.travel.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, TravelActivity.class));
        });
    }
}
