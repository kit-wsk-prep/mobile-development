package com.example.moduleb1.Tickets;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.moduleb1.Events.EventsActivity;
import com.example.moduleb1.MainActivity;
import com.example.moduleb1.Records.RecordsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityTicketsBinding;

public class TicketsActivity extends AppCompatActivity {
    private ActivityTicketsBinding binding;

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.navigation_view)));
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(TicketsActivity.this, MainActivity.class));
            } else if (id == R.id.nav_events) {
                startActivity(new Intent(TicketsActivity.this, EventsActivity.class));
            } else if (id == R.id.nav_tickets) {
                startActivity(new Intent(TicketsActivity.this, RecordsActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}
