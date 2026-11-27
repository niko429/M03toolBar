package com.napertyhryhorenko.m03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    public static final String EXTRA_UNIT_SYSTEM = "extra_unit_system";
    public static final String UNIT_METRIC = "metric";
    public static final String UNIT_IMPERIAL = "imperial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = findViewById(R.id.radioGroupUnits);
        Button btnSave = findViewById(R.id.btnSave);

        String currentUnit = getIntent().getStringExtra(EXTRA_UNIT_SYSTEM);
        if (UNIT_IMPERIAL.equals(currentUnit)) {
            radioGroup.check(R.id.rbImperial);
        } else {
            radioGroup.check(R.id.rbMetric);
        }

        btnSave.setOnClickListener(v -> {
            String selectedUnit = (radioGroup.getCheckedRadioButtonId() == R.id.rbImperial)
                    ? UNIT_IMPERIAL
                    : UNIT_METRIC;

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_UNIT_SYSTEM, selectedUnit);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}