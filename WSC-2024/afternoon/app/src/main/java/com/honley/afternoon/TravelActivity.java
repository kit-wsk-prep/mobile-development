package com.honley.afternoon;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.honley.afternoon.databinding.ActivityHomeBinding;
import com.honley.afternoon.databinding.ActivityTravelBinding;

public class TravelActivity extends AppCompatActivity {
    private ActivityTravelBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTravelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        String emailPreferences = sharedPreferences.getString("email", null);
        String passwordPreferences = sharedPreferences.getString("password", null);

        if (emailPreferences.isEmpty() && passwordPreferences.isEmpty()) {
            binding.account.setOnClickListener(v -> {
                startActivity(new Intent(TravelActivity.this, MainActivity.class));
            });
        } else {
            binding.account.setOnClickListener(v -> {
                startActivity(new Intent(TravelActivity.this, AccountActivity.class));
            });
        }

        binding.home.setOnClickListener(v -> {
            startActivity(new Intent(TravelActivity.this, HomeActivity.class));
        });

        binding.toureiffel.setOnClickListener(v -> {
            String place = "toureiffel";
            System.out.println(place);
            dialog(place);
        });

        binding.socrecoeur.setOnClickListener(v -> {
            String place = "socrecoeur";
            System.out.println(place);
            dialog(place);
        });

        binding.arcdetriophe.setOnClickListener(v -> {
            String place = "arcdetriophe";
            System.out.println(place);
            dialog(place);
        });

        binding.palaisRoyal.setOnClickListener(v -> {
            String place = "palaisRoyal";
            System.out.println(place);
            dialog(place);
        });
    }

    @SuppressLint("ResourceType")
    private void dialog(String place) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_place);
        dialog.setCancelable(true);

        ImageView placeImage = dialog.findViewById(R.id.placeImgae);
        TextView placeText = dialog.findViewById(R.id.placeText);

        switch (place) {
            case "toureiffel":
                placeImage.setImageResource(R.drawable.scene_1); // Устанавливаем изображение
                placeText.setText(R.string.toureiffel);          // Устанавливаем текст
                break;

            case "socrecoeur":
                placeImage.setImageResource(R.drawable.scene_5);
                placeText.setText(R.string.socrecoeur);
                break;

            case "arcdetriophe":
                placeImage.setImageResource(R.drawable.scene_2);
                placeText.setText(R.string.arcdetriophe);
                break;

            case "palaisRoyal":
                placeImage.setImageResource(R.drawable.scene_4);
                placeText.setText(R.string.palaisRoyal);
                break;

            default:
                placeImage.setImageResource(R.drawable.icon_close);
                placeText.setText(R.string.content);
                break;
        }

        dialog.show();
    }
}
