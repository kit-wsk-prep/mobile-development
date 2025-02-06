package com.example.moduleb1.Tickets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.moduleb1.Models.Event;
import com.example.moduleb1.Events.EventsActivity;
import com.example.moduleb1.MainActivity;
import com.example.moduleb1.Models.TicketType;
import com.example.moduleb1.Records.RecordsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityCreateTicketBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateTicket extends AppCompatActivity {
    private ActivityCreateTicketBinding binding;

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private NavigationView navigationView;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateTicketBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        menuButton.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.navigation_view)));
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(CreateTicket.this, MainActivity.class));
            } else if (id == R.id.nav_events) {
                startActivity(new Intent(CreateTicket.this, EventsActivity.class));
            } else if (id == R.id.nav_tickets) {
                startActivity(new Intent(CreateTicket.this, TicketsActivity.class));
            } else if (id == R.id.nav_records) {
                startActivity(new Intent(CreateTicket.this, RecordsActivity.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

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
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        Spinner spinner = findViewById(R.id.spinner);

        // Изменяем адаптер, чтобы использовать eventTitle
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Arrays.stream(TicketType.values())
                        .map(TicketType::getEventTitle) // Используем eventTitle
                        .collect(Collectors.toList())
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedEventTitle = (String) parentView.getItemAtPosition(position);
                TicketType selectedTicketType = TicketType.fromEventTitle(selectedEventTitle);

                if (selectedTicketType != null) {
                    // Теперь у нас есть выбранный TicketType
                    // Можно работать с eventTitle и ticketType как нужно
                    SharedPreferences sharedPreferences = getSharedPreferences("tickets", MODE_PRIVATE);
                    sharedPreferences.edit()
                            .putString("selectedTicketType", selectedTicketType.name())
                            .putString("selectedEventTitle", selectedEventTitle)
                            .apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Если ничего не выбрано
            }
        });

        binding.image.setOnClickListener(view -> pickImageLauncher.launch("image/*"));

        binding.send.setOnClickListener(view -> {
            String name = binding.name.getText().toString();
            String selectedEventTitle = binding.spinner.getSelectedItem().toString();  // Получаем выбранный eventTitle из Spinner

            if (selectedImageUri != null && !name.isEmpty()) {
                saveTicket(name, selectedImageUri.toString(), selectedEventTitle); // Передаем eventTitle в saveTicket

                Toast.makeText(CreateTicket.this, "Билет успешно создан", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateTicket.this, TicketsActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please select an image and enter a name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveTicket(String name, String imageUri, String eventTitle) {
        SharedPreferences sharedPreferences = getSharedPreferences("tickets", MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("tickets", "[]");

        ArrayList<String> tickets = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                tickets.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject ticket = new JSONObject();
        try {
            ticket.put("name", name);
            ticket.put("imageUri", imageUri);
            ticket.put("eventTitle", eventTitle);  // Добавляем eventTitle в JSON
        } catch (JSONException e) {
            e.printStackTrace();
        }

        tickets.add(ticket.toString());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tickets", new JSONArray(tickets).toString());
        editor.apply();
    }

    private final ActivityResultLauncher<String> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    binding.image.setImageURI(uri);
                }
            }
    );

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
