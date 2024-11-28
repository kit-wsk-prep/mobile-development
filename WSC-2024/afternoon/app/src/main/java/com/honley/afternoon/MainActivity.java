package com.honley.afternoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.honley.afternoon.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        String email = binding.email.getText().toString();
        String password = binding.password.getText().toString();

        EditText passwordEditText = findViewById(R.id.password);
        ImageView visibilityToggle = findViewById(R.id.visible);

        final boolean[] isPasswordVisible = {false};

        visibilityToggle.setOnClickListener(v -> {
            if (isPasswordVisible[0]) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                visibilityToggle.setImageResource(R.drawable.icon_eye_circle);
            } else {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                visibilityToggle.setImageResource(R.drawable.icon_eye_circle);
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
            isPasswordVisible[0] = !isPasswordVisible[0];
        });

        binding.home.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        });

        binding.travel.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TravelActivity.class));
        });

        binding.sign.setOnClickListener(v -> {
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}
