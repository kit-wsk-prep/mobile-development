package com.example.moduleb1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.moduleb1.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        String data = readJsonFromRaw();
        try {
            JSONArray events = new JSONArray(data);

            for (int i = 0; i < events.length(); i++) {
                JSONObject event = events.getJSONObject(i);

                String eventId = event.getString("eventId");
                String eventTitle = event.getString("eventTitle");
                String eventText = event.getString("eventText");
                boolean eventReadStatus = event.getBoolean("eventReadStatus");

                JSONArray eventPictures = event.getJSONArray("eventPictures");
                for (int j = 0; j < eventPictures.length(); j++) {
                    String picture = eventPictures.getString(j);
                    Log.d("JSON", "Picture: " + picture);
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
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