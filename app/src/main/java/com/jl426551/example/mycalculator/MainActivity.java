package com.jl426551.example.mycalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.jl426551.example.mycalculator.ButtonData.CLEAR;
import static com.jl426551.example.mycalculator.ButtonData.DOUBLE_ZERO;
import static com.jl426551.example.mycalculator.ButtonData.EQUALS;

public class MainActivity extends AppCompatActivity implements ButtonDataAdapter.ButtonClickHandler {

    TextView displayView;
    TextView resultView;
    String mOperation;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    ButtonDataAdapter adapter;
    ArrayList<ButtonData> dataList;

    boolean clearDisplayPriorToType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rv);

        //Get fixed data since our ButtonData list will never change in this app.
        dataList = ButtonData.getStaticButtonList();

        //Initialize Views
        resultView = findViewById(R.id.results_view);
        displayView = findViewById(R.id.input_view);
        recyclerView = findViewById(R.id.my_recycler_view);

        //Create a grid layout with 5 spans
        manager = new GridLayoutManager(getBaseContext(), 5);
        adapter = new ButtonDataAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        mOperation = "";
        clearDisplayPriorToType = false;
    }

    //Adds a character to the calculator view to prepare it for operation.
    public void addCharacter(char entered) {
        if (clearDisplayPriorToType)
            mOperation = "";

        mOperation += entered;
        updateDisplay();
        clearDisplayPriorToType = false;
    }

    private void clearOperation() {
        clearDisplayPriorToType = false;
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
            clearDisplayPriorToType = true;
        } else {
            //Toast error message
            Toast.makeText(this, "Improper input entered. Clearing field.", Toast.LENGTH_SHORT).show();
            clearOperation();
            clearDisplayPriorToType = true;
        }

    }

    //Read from the ButtonDataAdapter.
    @Override
    public void onButtonSelected(int charOption) {
        switch (charOption) {
            case CLEAR:
                clearOperation();
                break;
            case EQUALS:
                calculateOperation();
                break;
            case DOUBLE_ZERO:
                addCharacter('0');
                addCharacter('0');
                break;
            //Since our list does not change a one-to-one value to position matches characters listed.
            default:
                addCharacter(dataList.get(charOption).getDisplayValue().charAt(0));
        }
    }
}
