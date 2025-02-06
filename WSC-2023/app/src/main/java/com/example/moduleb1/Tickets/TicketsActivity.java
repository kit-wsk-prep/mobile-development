package com.example.moduleb1.Tickets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.moduleb1.Events.EventsActivity;
import com.example.moduleb1.MainActivity;
import com.example.moduleb1.Models.Ticket;
import com.example.moduleb1.Records.RecordsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityTicketsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
            } else if (id == R.id.nav_records) {
                startActivity(new Intent(TicketsActivity.this, RecordsActivity.class));
            } else if (id == R.id.nav_create_tickets) {
                startActivity(new Intent(TicketsActivity.this, CreateTicket.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        binding.createTicket.setOnClickListener(view -> {
            Intent intent = new Intent(TicketsActivity.this, CreateTicket.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = getSharedPreferences("tickets", MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("tickets", "[]");

        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ticketJson = jsonArray.getJSONObject(i);
                String name = ticketJson.getString("name");
                String imageUri = ticketJson.getString("imageUri");

                Ticket ticket = new Ticket(name, imageUri);
                tickets.add(ticket);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Tickets: " + tickets);
    }
}
