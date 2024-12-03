package com.example.hitfirstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //instance variables declaration
    private TextView result;
    private double firstNum;
    private double secondNum;
    private char operator = 0;
    private boolean allowedToType = true; //flag that allowed or disallowed to add numbers to the screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Synchronizing result with the text view
        result = findViewById(R.id.textViewResult);
        result.setText("");
    }

    public void numFunction(View view) { //Adds numbers to the screen one after the other

        //condition that starts new calculation when user type number immediately after equal button
        // without pressing an operator before that
        if(operator == 0 && !allowedToType ) {
            result.setText("");
            allowedToType = true;
        }

        if(allowedToType) {
            Button button = (Button) view;
            result.append(button.getText().toString());
        }
    }

    public void operatorFunction(View view) { //managing the action when user press on operator
        if(!result.getText().toString().isEmpty()) {
            allowedToType = true;
            Button button = (Button) view;
            operator = button.getText().toString().charAt(0);
            firstNum = Double.parseDouble(result.getText().toString());
            result.setText("");
        }
    }

    public void equalFunction(View view) { //managing the action when user press on equal
        double res;

        if(result.getText().toString().isEmpty()){
            return;
        }
        if(operator == 0 && !result.getText().toString().isEmpty()){
            return;
        }

        allowedToType = false;
        secondNum = Double.parseDouble(result.getText().toString());
        result.setText("");

        switch (operator){ //calculating the result depends on the operator
            case '+':
                res = firstNum + secondNum;
                result.setText(String.valueOf(res));
                break;
            case '-':
                res = firstNum - secondNum;
                result.setText(String.valueOf(res));
                break;
            case 'X':
                res = firstNum*secondNum;
                result.setText(String.valueOf(res));
                break;
            case '/':
                if(secondNum == 0){ //devide in zero case
                   result.setText("ERROR");
                }
                else {
                    res = firstNum / secondNum;
                    result.setText(String.valueOf(res));
                }
                break;
            default:
                res = 0;
                result.setText(String.valueOf(res));
        }

        operator = 0;
    }

    public void clearFunction(View view) { //clear the screen
        result.setText("");
        allowedToType = true;
    }
}