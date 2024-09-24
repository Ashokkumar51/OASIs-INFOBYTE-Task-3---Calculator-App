package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = 0;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);
    }

    // Handle digit button clicks
    public void onDigit(View view) {
        Button button = (Button) view;
        if (isOperatorClicked) {
            currentInput = ""; // Start new number input after operator
            isOperatorClicked = false;
        }
        currentInput += button.getText().toString();
        tvInput.setText(currentInput);
    }

    // Handle operator button clicks
    public void onOperator(View view) {
        Button button = (Button) view;
        if (!currentInput.equals("")) {
            firstValue = Double.parseDouble(currentInput);
            operator = button.getText().toString();
            isOperatorClicked = true;
            tvInput.setText(currentInput + " " + operator); // Display the operator along with the first number
        }
    }

    // Handle equal button click
    public void onEqual(View view) {
        if (!currentInput.equals("") && !operator.equals("")) {
            double secondValue = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) {
                        result = firstValue / secondValue;
                    } else {
                        tvInput.setText("Error"); // Handle divide by zero error
                        return;
                    }
                    break;
            }
            tvInput.setText(String.valueOf(result));
            currentInput = String.valueOf(result); // Store result for further calculations
            operator = "";
        }
    }

    // Handle clear button click
    public void onClear(View view) {
        currentInput = "";
        operator = "";
        firstValue = 0;
        isOperatorClicked = false;
        tvInput.setText("0");
    }
}
