package com.napertyhryhorenko.m03;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "pl.edu._4tp.shopapp.EXTRA_NAME";
    public static final String EXTRA_PRICE = "pl.edu._4tp.shopapp.EXTRA_PRICE";
    public static final String EXTRA_AVAILABLE = "pl.edu._4tp.shopapp.EXTRA_AVAILABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        double price = intent.getDoubleExtra(EXTRA_PRICE, 0.0);
        boolean available = intent.getBooleanExtra(EXTRA_AVAILABLE, false);

        TextView textName = findViewById(R.id.text_name);
        TextView textPrice = findViewById(R.id.text_price);
        TextView textAvailable = findViewById(R.id.text_available);

        textName.setText(name);
        textPrice.setText(getString(R.string.price_format, price));
        textAvailable.setText(available ? R.string.available_yes : R.string.available_no);

        findViewById(R.id.button_share).setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, name, price));
            startActivity(Intent.createChooser(share, getString(R.string.share_via)));
        });

        findViewById(R.id.button_call).setOnClickListener(v -> {
            Intent dial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:757000000"));
            try {
                startActivity(dial);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        });
    }
}