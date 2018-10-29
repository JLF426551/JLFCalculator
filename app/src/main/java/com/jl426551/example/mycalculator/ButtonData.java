package com.jl426551.example.mycalculator;

import java.util.ArrayList;

public class ButtonData {

    char displayValue;

    public ButtonData(char a)
    {
        displayValue = a;
    }

    public char getDisplayValue()
    {
        return displayValue;
    }

    public static ArrayList<ButtonData> getStaticButtonList()
    {
        ArrayList<ButtonData> data = new ArrayList<>();

        data.add( new ButtonData('-' ));
        data.add( new ButtonData( '7'));
        data.add( new ButtonData( '8'));
        data.add( new ButtonData( '9'));
        data.add( new ButtonData( '<'));
        data.add( new ButtonData( '+'));
        data.add( new ButtonData( '4'));
        data.add( new ButtonData( '5'));
        data.add( new ButtonData('6' ));
        data.add( new ButtonData( '/'));
        data.add( new ButtonData( '1'));
        data.add( new ButtonData( '2'));
        data.add( new ButtonData( '3'));
        data.add( new ButtonData( '='));
        data.add( new ButtonData( 'x'));
        data.add( new ButtonData( '0'));
        data.add( new ButtonData( '.'));

        return data;
    }
}
