package com.napertyhryhorenko.m03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

public class AddProductActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "pl.edu._4tp.shopapp.ADD_NAME";
    public static final String EXTRA_PRICE = "pl.edu._4tp.shopapp.ADD_PRICE";

    private EditText editName;
    private EditText editPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editName = findViewById(R.id.edit_name);
        editPrice = findViewById(R.id.edit_price);

        findViewById(R.id.button_save).setOnClickListener(v -> {
            if (!validate()) {
                return;
            }
            Intent data = new Intent();
            data.putExtra(EXTRA_NAME, editName.getText().toString().trim());
            data.putExtra(EXTRA_PRICE, Double.parseDouble(editPrice.getText().toString().trim()));
            setResult(RESULT_OK, data);
            finish();
        });

        findViewById(R.id.button_cancel).setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    private boolean validate() {
        boolean valid = true;
        if (editName.getText().toString().trim().isEmpty()) {
            editName.setError(getString(R.string.error_required));
            valid = false;
        }
        if (editPrice.getText().toString().trim().isEmpty()) {
            editPrice.setError(getString(R.string.error_required));
            valid = false;
        }
        return valid;
    }
}