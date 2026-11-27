
package com.napertyhryhorenko.m03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    public static final String EXTRA_BMI = "extra_bmi";
    public static final String EXTRA_CATEGORY = "extra_category";
    public static final String EXTRA_WEIGHT = "extra_weight";
    public static final String EXTRA_HEIGHT = "extra_height";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView txtDetails = findViewById(R.id.txtDetails);

        double bmi = getIntent().getDoubleExtra(EXTRA_BMI, 0.0);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        double weight = getIntent().getDoubleExtra(EXTRA_WEIGHT, 0.0);
        double height = getIntent().getDoubleExtra(EXTRA_HEIGHT, 0.0);

        String details = String.format(
                java.util.Locale.getDefault(),
                "SZCZEGÓŁY BMI\n\n" +
                        "Waga: %.1f kg\n" +
                        "Wzrost: %.1f cm\n" +
                        "Wynik BMI: %.2f\n" +
                        "Kategoria: %s",
                weight, height, bmi, category
        );

        txtDetails.setText(details);
    }
}
