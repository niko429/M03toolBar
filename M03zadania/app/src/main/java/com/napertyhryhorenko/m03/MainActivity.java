package com.napertyhryhorenko.m03;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnStrona, btnTelefon, btnEmail, btnMapa, btnUdostepnij;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStrona = findViewById(R.id.btnStrona);
        btnTelefon = findViewById(R.id.btnTelefon);
        btnEmail = findViewById(R.id.btnEmail);
        btnMapa = findViewById(R.id.btnMapa);
        btnUdostepnij = findViewById(R.id.btnUdostepnij);

        // STRONA WWW
        btnStrona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.google.com"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,
                            "Brak przeglądarki", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TELEFON
        btnTelefon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.parse("tel:123456789"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,
                            "Brak aplikacji telefonu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // E-MAIL
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO,
                            Uri.parse("mailto:kontakt@gmail.com"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,
                            "Brak aplikacji pocztowej", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // MAPA
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo:52.2297,21.0122?q=Warszawa"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,
                            "Brak aplikacji map", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // UDOSTĘPNIANIE
        btnUdostepnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT,
                            "Kontakt: https://www.google.com");

                    startActivity(Intent.createChooser(intent,
                            "Udostępnij przez"));

                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this,
                            "Brak aplikacji do udostępniania",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
