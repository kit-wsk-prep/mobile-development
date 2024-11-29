package com.honley.afternoon;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.honley.afternoon.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        String emailPreferences = sharedPreferences.getString("email", null);
        String passwordPreferences = sharedPreferences.getString("password", null);

        if (emailPreferences.isEmpty() && passwordPreferences.isEmpty()) {
            startActivity(new Intent(AccountActivity.this, MainActivity.class));
        } else {
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
                startActivity(new Intent(AccountActivity.this, HomeActivity.class));
            });

            binding.travel.setOnClickListener(v -> {
                startActivity(new Intent(AccountActivity.this, TravelActivity.class));
            });

            binding.signout.setOnClickListener(v -> {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                startActivity(new Intent(AccountActivity.this, MainActivity.class));
            });
        }
    }
}
