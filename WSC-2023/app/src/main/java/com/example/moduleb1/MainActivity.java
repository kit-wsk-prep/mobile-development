package com.example.moduleb1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.moduleb1.Events.EventsActivity;
import com.example.moduleb1.Records.RecordsActivity;
import com.example.moduleb1.Tickets.TicketsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private NavigationView navigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding.events.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, EventsActivity.class)));
        binding.tickets.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, TicketsActivity.class)));

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.navigation_view)));
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            } else if (id == R.id.nav_events) {
                startActivity(new Intent(MainActivity.this, EventsActivity.class));
            } else if (id == R.id.nav_tickets) {
                startActivity(new Intent(MainActivity.this, TicketsActivity.class));
            } else if (id == R.id.nav_records) {
                startActivity(new Intent(MainActivity.this, RecordsActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}