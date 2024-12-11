package com.honley.afternoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.honley.afternoon.Favourite.FavouriteAdapter;
import com.honley.afternoon.Favourite.FavouriteItem;
import com.honley.afternoon.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private FavouriteAdapter favouriteAdapter;
    private List<FavouriteItem> favouriteItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String emailPreferences = sharedPreferences.getString("email", null);
        String passwordPreferences = sharedPreferences.getString("password", null);

        try {
            if (emailPreferences.isEmpty() && passwordPreferences.isEmpty()) {
                binding.account.setOnClickListener(v -> {
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                });
            } else {
                binding.account.setOnClickListener(v -> {
                    startActivity(new Intent(HomeActivity.this, AccountActivity.class));
                });
            }
        } catch (NullPointerException exception) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }

        binding.travel.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, TravelActivity.class));
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Set<String> favouriteSet = sharedPreferences.getStringSet("favourite", new HashSet<>());

        ArrayList<FavouriteItem> favouriteList = new ArrayList<>();
        for (String item : favouriteSet) {
            FavouriteItem favouriteItem = new FavouriteItem(item);
            favouriteList.add(favouriteItem);
        }

        favouriteAdapter = new FavouriteAdapter(this, favouriteList);
        binding.recyclerView.setAdapter(favouriteAdapter);

    }
}
