package com.example.moduleb1.Events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moduleb1.MainActivity;
import com.example.moduleb1.Models.Event;
import com.example.moduleb1.Records.RecordsActivity;
import com.example.moduleb1.Tickets.CreateTicket;
import com.example.moduleb1.Tickets.TicketsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityEventsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private ActivityEventsBinding binding;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String data = readJsonFromRaw();
        List<Event> eventList = new ArrayList<>();
        try {
            JSONArray events = new JSONArray(data);
            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);

                String eventId = event.getString("eventId");
                String eventTitle = event.getString("eventTitle");
                String eventText = event.getString("eventText");
                boolean eventReadStatus = event.getBoolean("eventReadStatus");

                JSONArray eventPicturesArray = event.getJSONArray("eventPictures");
                List<String> eventPictures = new ArrayList<>();
                for (int j = 0; j < eventPicturesArray.length(); j++) {
                    eventPictures.add(eventPicturesArray.getString(j));
                }

                eventList.add(new Event(eventId, eventTitle, eventText, eventReadStatus, eventPictures));
            }

            eventAdapter = new EventAdapter(eventList);
            recyclerView.setAdapter(eventAdapter);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.navigation_view)));
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(EventsActivity.this, MainActivity.class));
            } else if (id == R.id.nav_tickets) {
                startActivity(new Intent(EventsActivity.this, TicketsActivity.class));
            } else if (id == R.id.nav_records) {
                startActivity(new Intent(EventsActivity.this, RecordsActivity.class));
            } else if (id == R.id.nav_create_tickets) {
                startActivity(new Intent(EventsActivity.this, CreateTicket.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    public String readJsonFromRaw() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.events_data);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
