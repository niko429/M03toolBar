package com.napertyhryhorenko.m03;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight;
    private EditText etHeight;
    private TextView tvResult;
    private Button btnDetails;

    private String currentUnit = SettingsActivity.UNIT_METRIC;

    private double lastBmi = 0.0;
    private String lastCategory = "";
    private double lastWeight = 0.0;
    private double lastHeight = 0.0;

    private final ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String chosenUnit = result.getData().getStringExtra(SettingsActivity.EXTRA_UNIT_SYSTEM);
                    if (chosenUnit != null) {
                        currentUnit = chosenUnit;

                        etWeight.setText("");
                        etHeight.setText("");
                        updateInputHints();

                        tvResult.setText("Wynik pojawi się tutaj");
                        btnDetails.setVisibility(View.GONE);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Kalkulator BMI");
        }

        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        tvResult = findViewById(R.id.tvResult);
        btnDetails = findViewById(R.id.btnDetails);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(v -> calculateBmi());

        btnDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra(DetailsActivity.EXTRA_BMI, lastBmi);
            intent.putExtra(DetailsActivity.EXTRA_CATEGORY, lastCategory);
            intent.putExtra(DetailsActivity.EXTRA_WEIGHT, lastWeight);
            intent.putExtra(DetailsActivity.EXTRA_HEIGHT, lastHeight);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            intent.putExtra(SettingsActivity.EXTRA_UNIT_SYSTEM, currentUnit);
            settingsLauncher.launch(intent);
            return true;
        } else if (id == R.id.action_info) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateBmi() {
        String weightStr = etWeight.getText().toString().trim();
        String heightStr = etHeight.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Wpisz wagę i wzrost!", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        if (weight <= 0 || height <= 0) {
            Toast.makeText(this, "Wartości muszą być większe od zera!", Toast.LENGTH_SHORT).show();
            return;
        }

        double bmi;
        if (SettingsActivity.UNIT_IMPERIAL.equals(currentUnit)) {
            bmi = (weight / (height * height)) * 703.0;
        } else {
            double heightInMeters = height / 100.0;
            bmi = weight / (heightInMeters * heightInMeters);
        }

        String category = getBmiCategory(bmi);

        lastBmi = bmi;
        lastCategory = category;
        lastWeight = weight;
        lastHeight = height;

        tvResult.setText(String.format(Locale.getDefault(), "Twoje BMI: %.2f\n(%s)", bmi, category));
        btnDetails.setVisibility(View.VISIBLE);
    }

    private String getBmiCategory(double bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25.0) {
            return "Waga prawidłowa";
        } else if (bmi < 30.0) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }

    private void updateInputHints() {
        if (SettingsActivity.UNIT_IMPERIAL.equals(currentUnit)) {
            etWeight.setHint("Waga (lbs)");
            etHeight.setHint("Wzrost (in)");
        } else {
            etWeight.setHint("Waga (kg)");
            etHeight.setHint("Wzrost (cm)");
        }
    }
}