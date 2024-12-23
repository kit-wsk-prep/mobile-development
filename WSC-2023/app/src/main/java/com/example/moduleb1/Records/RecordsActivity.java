package com.example.moduleb1.Records;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.moduleb1.Events.EventsActivity;
import com.example.moduleb1.MainActivity;
import com.example.moduleb1.Tickets.TicketsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityRecordsBinding;

public class RecordsActivity extends AppCompatActivity {
    private ActivityRecordsBinding binding;

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.navigation_view)));
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(RecordsActivity.this, MainActivity.class));
            } else if (id == R.id.nav_events) {
                startActivity(new Intent(RecordsActivity.this, EventsActivity.class));
            } else if (id == R.id.nav_tickets) {
                startActivity(new Intent(RecordsActivity.this, TicketsActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}
