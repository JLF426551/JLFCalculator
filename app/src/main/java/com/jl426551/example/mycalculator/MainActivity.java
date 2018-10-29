package com.jl426551.example.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView displayView;
    TextView resultView;
    String mOperation;
    Button zeroButton;
    Button oneButton;
    Button twoButton;
    Button threeButton;
    Button fourButton;
    Button fiveButton;
    Button sixButton;
    Button sevenButton;
    Button eightButton;
    Button nineButton;
    Button periodButton;
    Button doubleZeroButton;
    Button plusButton;
    Button minusButton;
    Button multiplyButton;
    Button divideButton;
    Button equalsButton;
    Button openButton;
    Button closeButton;
    Button clearButton;

    View.OnClickListener oneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('1');
        }
    };

    View.OnClickListener twoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('2');
        }
    };

    View.OnClickListener threeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('3');
        }
    };

    View.OnClickListener fourListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('4');
        }
    };

    View.OnClickListener fiveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('5');
        }
    };

    View.OnClickListener sixListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('6');
        }
    };

    View.OnClickListener sevenListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('7');
        }
    };

    View.OnClickListener eightListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('8');
        }
    };

    View.OnClickListener nineListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('9');
        }
    };

    View.OnClickListener zeroListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('0');
        }
    };

    View.OnClickListener doubleZeroListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('0');
            addCharacter('0');
        }
    };

    View.OnClickListener periodListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('.');
        }
    };

    View.OnClickListener plusListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('+');
        }
    };

    View.OnClickListener minusListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('-');
        }
    };

    View.OnClickListener multiplyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('*');
        }
    };

    View.OnClickListener divideListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener openListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter('(');
        }
    };

    View.OnClickListener closeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addCharacter(')');
        }
    };

    View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clearOperation();
        }
    };

    View.OnClickListener equalsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculateOperation();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Views
        resultView = findViewById(R.id.results_view);
        displayView = findViewById(R.id.input_view);
        zeroButton = findViewById(R.id.button_zero);
        oneButton = findViewById(R.id.button_one);
        twoButton = findViewById(R.id.button_two);
        threeButton = findViewById(R.id.button_three);
        fourButton = findViewById(R.id.button_four);
        fiveButton = findViewById(R.id.button_five);
        sixButton = findViewById(R.id.button_six);
        sevenButton = findViewById(R.id.button_seven);
        eightButton = findViewById(R.id.button_eight);
        nineButton = findViewById(R.id.button_nine);
        periodButton = findViewById(R.id.button_period);
        doubleZeroButton = findViewById(R.id.button_double_zero);
        plusButton = findViewById(R.id.button_addition);
        minusButton = findViewById(R.id.button_subtraction);
        multiplyButton = findViewById(R.id.button_multiplication);
        divideButton = findViewById(R.id.button_division);
        equalsButton = findViewById(R.id.button_equals);
        openButton = findViewById(R.id.button_open_parenthesis);
        closeButton = findViewById(R.id.button_close_parenthesis);
        clearButton = findViewById(R.id.button_backspace);

        //set-up listeners
        zeroButton.setOnClickListener(zeroListener);
        oneButton.setOnClickListener(oneListener);
        twoButton.setOnClickListener(twoListener);
        threeButton.setOnClickListener(threeListener);
        fourButton.setOnClickListener(fourListener);
        fiveButton.setOnClickListener(fiveListener);
        sixButton.setOnClickListener(sixListener);
        sevenButton.setOnClickListener(sevenListener);
        eightButton.setOnClickListener(eightListener);
        nineButton.setOnClickListener(nineListener);
        periodButton.setOnClickListener(periodListener);
        doubleZeroButton.setOnClickListener(doubleZeroListener);
        plusButton.setOnClickListener(plusListener);
        minusButton.setOnClickListener(minusListener);
        multiplyButton.setOnClickListener(multiplyListener);
        divideButton.setOnClickListener(divideListener);
        equalsButton.setOnClickListener(equalsListener);
        openButton.setOnClickListener(openListener);
        closeButton.setOnClickListener(closeListener);
        clearButton.setOnClickListener(clearListener);


        mOperation = "";
    }

    private void addCharacter(char entered) {
        mOperation += entered;
        updateDisplay();
    }

    private void clearOperation() {
        mOperation = "";
        updateDisplay();
    }

    private void updateDisplay() {
        resultView.setText(mOperation);
    }

    private void calculateOperation() {

        if (Calculator.VerifyString(mOperation)) {
            String mResult = Calculator.getResult(mOperation);
            resultView.setText(mResult);
            displayView.setText(mOperation);
        } else {
            //Toast error message
            clearOperation();
        }

    }
}
