package com.napertyhryhorenko.m03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button btnChleb, btnMleko, btnMaslo;
    TextView txtOstatnioDodano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChleb = findViewById(R.id.btnChleb);
        btnMleko = findViewById(R.id.btnMleko);
        btnMaslo = findViewById(R.id.btnMaslo);

        txtOstatnioDodano = findViewById(R.id.txtOstatnioDodano);

        // PRZYWRACANIE DANYCH
        if (savedInstanceState != null) {
            String produkt = savedInstanceState.getString("ostatniProdukt");
            txtOstatnioDodano.setText(produkt);
        }

        // DODAWANIE CHLEBA
        btnChleb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOstatnioDodano.setText("Ostatnio dodano: Chleb");
            }
        });

        // DODAWANIE MLEKA
        btnMleko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOstatnioDodano.setText("Ostatnio dodano: Mleko");
            }
        });

        // DODAWANIE MASŁA
        btnMaslo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOstatnioDodano.setText("Ostatnio dodano: Masło");
            }
        });
    }

    // ZAPISYWANIE DANYCH
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("ostatniProdukt",
                txtOstatnioDodano.getText().toString());
    }
}
