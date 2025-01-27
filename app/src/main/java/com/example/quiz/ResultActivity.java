package com.example.quiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result); // Inflar el diseño correcto

        tvResult = findViewById(R.id.tvResult); // Utilizar el id correcto

        double result = getIntent().getDoubleExtra("result", 0);
        tvResult.setText("Resultado: " + result);
    }
}
