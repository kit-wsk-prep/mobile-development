package com.example.moduleb1.Tickets;

import static android.widget.Toast.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private RecyclerView ticketsRecyclerView;
    private TicketAdapter ticketAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTicketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        // Настройка RecyclerView
        ticketsRecyclerView = findViewById(R.id.tickets_recycler_view);  // Убедитесь, что этот элемент есть в XML разметке
        ticketsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
            // Преобразуем jsonString в JSONArray, так как это массив строк JSON
            JSONArray jsonArray = new JSONArray(jsonString);

            // Проходим по каждому элементу в массиве и парсим его как JSONObject
            for (int i = 0; i < jsonArray.length(); i++) {
                // Извлекаем строку JSON
                String ticketJsonString = jsonArray.getString(i);

                // Преобразуем строку JSON в JSONObject
                JSONObject ticketJson = new JSONObject(ticketJsonString);

                String name = ticketJson.getString("name");
                String imageUri = ticketJson.getString("imageUri");
                String eventTitle = ticketJson.getString("eventTitle");

                Ticket ticket = new Ticket(name, imageUri, eventTitle);
                tickets.add(ticket);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(tickets);
        ticketAdapter = new TicketAdapter(tickets);
        ticketsRecyclerView.setAdapter(ticketAdapter);
    }
}
