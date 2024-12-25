package com.example.moduleb1.Records;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.moduleb1.Events.EventsActivity;
import com.example.moduleb1.MainActivity;
import com.example.moduleb1.Tickets.TicketsActivity;
import com.google.android.material.navigation.NavigationView;
import com.honley.wsc_2023.R;
import com.honley.wsc_2023.databinding.ActivityRecordsBinding;

import java.io.File;
import java.io.IOException;

public class RecordsActivity extends AppCompatActivity {
    private ActivityRecordsBinding binding;

    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private NavigationView navigationView;

    private Button startRecordingButton;
    private Button stopRecordingButton;
    private Button playButton;

    private static int MICROPHONE_PERMISSION_CODE = 200;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        menuButton = findViewById(R.id.menu);
        navigationView = findViewById(R.id.navigation_view);

        startRecordingButton = findViewById(R.id.start_recording);
        stopRecordingButton = findViewById(R.id.stop_recording);
        playButton = findViewById(R.id.play_record);

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

        if (isMicrophonePresent()) {
            getMicrophonePermission();
        }
        startRecordingButton.setOnClickListener(v -> startRecording());
        stopRecordingButton.setOnClickListener(v -> stopRecording());
        playButton.setOnClickListener(v -> playRecording());
    }

    private void playRecording() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    private void startRecording() {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();

            Toast.makeText(this, "Recording started", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isMicrophonePresent() {
        return this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE);
    }

    private void getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE);
        }
    }

    private String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "record" + ".mp3");
        return file.getPath();
    }
}
