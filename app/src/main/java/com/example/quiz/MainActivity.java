package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1, etNumber2;
    private Spinner spinnerOperations;
    private Button btnCalculate;
    private String selectedOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        spinnerOperations = findViewById(R.id.spinnerOperations);
        btnCalculate = findViewById(R.id.btnCalculate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperations.setAdapter(adapter);

        spinnerOperations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperation = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedOperation = "Suma";
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNumber1.getText().toString().isEmpty() || etNumber2.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese ambos números.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double number1 = Double.parseDouble(etNumber1.getText().toString());
                double number2 = Double.parseDouble(etNumber2.getText().toString());
                double result = 0;

                switch (selectedOperation) {
                    case "Suma":
                        result = MathFunctions.suma(number1, number2);
                        break;
                    case "Resta":
                        result = MathFunctions.resta(number1, number2);
                        break;
                    case "Multiplicación":
                        result = MathFunctions.multiplicacion(number1, number2);
                        break;
                    case "División":
                        try {
                            result = MathFunctions.division(number1, number2);
                        } catch (IllegalArgumentException e) {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        break;
                }

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });
    }
}
