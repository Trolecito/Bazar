package com.example.bazzar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asocia las tarjetas con sus sonidos
        setupCard(R.id.card_Leon, "leon");
        setupCard(R.id.card_Buho, "buho");
        setupCard(R.id.card_Cerdo, "cerdo");
        setupCard(R.id.card_Gato, "gato");
        setupCard(R.id.card_Loro, "loro");
        setupCard(R.id.card_Mono, "mono");
        setupCard(R.id.card_Perro, "perro");
        setupCard(R.id.card_Pollo, "pollo");
        setupCard(R.id.card_Vaca, "vaca");
        setupCard(R.id.card_Zorro, "zorro");
    }

    private void setupCard(int cardId, final String animalName) {
        CardView card = findViewById(cardId);
        if (card != null) {
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Animal: " + capitalize(animalName), Toast.LENGTH_SHORT).show();

                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.release();
                            mediaPlayer = null;
                        }

                        int resId = getResources().getIdentifier(animalName, "raw", getPackageName());
                        if (resId == 0) {
                            Toast.makeText(MainActivity.this, "Audio no encontrado: " + animalName, Toast.LENGTH_SHORT).show();
                            return;
                        }

                        mediaPlayer = MediaPlayer.create(MainActivity.this, resId);
                        mediaPlayer.start();

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error al reproducir el sonido", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private String capitalize(String text) {
        if (text == null || text.length() == 0) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}