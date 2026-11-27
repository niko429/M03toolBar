package com.napertyhryhorenko.m03;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_BMI = "extra_bmi";
    public static final String EXTRA_CATEGORY = "extra_category";
    public static final String EXTRA_WEIGHT = "extra_weight";
    public static final String EXTRA_HEIGHT = "extra_height";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvDetails = findViewById(R.id.tvDetails);

        double bmi = getIntent().getDoubleExtra(EXTRA_BMI, 0.0);
        String category = getIntent().getStringExtra(EXTRA_CATEGORY);
        double weight = getIntent().getDoubleExtra(EXTRA_WEIGHT, 0.0);
        double height = getIntent().getDoubleExtra(EXTRA_HEIGHT, 0.0);

        String info = String.format(Locale.getDefault(),
                "Wynik BMI: %.2f\nKategoria: %s\nWaga: %.1f\nWzrost: %.1f",
                bmi, category, weight, height);

        tvDetails.setText(info);
    }
}