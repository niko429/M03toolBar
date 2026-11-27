
package com.napertyhryhorenko.m03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editWeight, editHeight;
    Button btnCalculate, btnDetails;
    TextView txtResult;

    double bmi;
    double weight;
    double height;
    String category;

    boolean calculated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnDetails = findViewById(R.id.btnDetails);
        txtResult = findViewById(R.id.txtResult);

        btnDetails.setEnabled(false);

        btnCalculate.setOnClickListener(v -> calculateBMI());

        btnDetails.setOnClickListener(v -> openDetails());
    }

    private void calculateBMI() {

        String weightText = editWeight.getText().toString();
        String heightText = editHeight.getText().toString();

        if (weightText.isEmpty() || heightText.isEmpty()) {
            Toast.makeText(this, "Wpisz wagę i wzrost!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            weight = Double.parseDouble(weightText.replace(',', '.'));
            height = Double.parseDouble(heightText.replace(',', '.'));

            if (weight <= 0 || height <= 0) {
                Toast.makeText(this, "Podaj wartości większe od zera!", Toast.LENGTH_SHORT).show();
                return;
            }

            bmi = weight / Math.pow(height / 100.0, 2);

            if (bmi < 18.5) {
                category = "Niedowaga";
            } else if (bmi < 25) {
                category = "Waga prawidłowa";
            } else if (bmi < 30) {
                category = "Nadwaga";
            } else {
                category = "Otyłość";
            }

            txtResult.setText(String.format(
                    java.util.Locale.getDefault(),
                    "BMI: %.2f\nKategoria: %s",
                    bmi, category
            ));

            calculated = true;
            btnDetails.setEnabled(true);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Niepoprawne dane!", Toast.LENGTH_SHORT).show();
        }
    }

    private void openDetails() {

        if (!calculated) {
            return;
        }

        Intent intent = new Intent(MainActivity.this, InfoActivity.class);

        intent.putExtra(InfoActivity.EXTRA_BMI, bmi);
        intent.putExtra(InfoActivity.EXTRA_CATEGORY, category);
        intent.putExtra(InfoActivity.EXTRA_WEIGHT, weight);
        intent.putExtra(InfoActivity.EXTRA_HEIGHT, height);

        startActivity(intent);
    }
}
