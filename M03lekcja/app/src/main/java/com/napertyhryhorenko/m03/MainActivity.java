package com.napertyhryhorenko.m03;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private TextView textLast;

    private final ActivityResultLauncher<Intent> addProductLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String name = result.getData().getStringExtra(AddProductActivity.EXTRA_NAME);
                    double price = result.getData().getDoubleExtra(AddProductActivity.EXTRA_PRICE, 0);
                    textLast.setText(getString(R.string.last_added, name, price));
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textLast = findViewById(R.id.text_last);

        findViewById(R.id.button_laptop).setOnClickListener(v -> openDetail("Laptop", 2999.0, true));
        findViewById(R.id.button_mouse).setOnClickListener(v -> openDetail("Mysz", 49.0, false));
    }

    private void openDetail(String name, double price, boolean available) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_NAME, name);
        intent.putExtra(DetailActivity.EXTRA_PRICE, price);
        intent.putExtra(DetailActivity.EXTRA_AVAILABLE, available);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            addProductLauncher.launch(new Intent(this, AddProductActivity.class));
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, R.string.about_text, Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}