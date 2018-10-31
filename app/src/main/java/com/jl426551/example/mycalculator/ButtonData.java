package com.jl426551.example.mycalculator;

import java.util.ArrayList;

public class ButtonData {

    public final static int MINUS = 0;
    public final static int SEVEN = 1;
    public final static int EIGHT = 2;
    public final static int NINE = 3;
    public final static int CLEAR = 4;

    public final static int PLUS= 5;
    public final static int FOUR= 6;
    public final static int FIVE= 7;
    public final static int SIX= 8;
    public final static int OPEN= 9;

    public final static int DIVIDE= 10;
    public final static int ONE = 11;
    public final static int TWO = 12;
    public final static int THREE = 13;
    public final static int CLOSE = 14;

    public final static int MULTIPLY= 15;
    public final static int ZERO = 16;
    public final static int DOUBLE_ZERO = 17;
    public final static int PERIOD = 18;
    public final static int EQUALS = 19;

    String displayValue;
    int assignment;

    public ButtonData(String display)
    {
        displayValue = display;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }

    public static ArrayList<ButtonData> getStaticButtonList()
    {
        ArrayList<ButtonData> data = new ArrayList<>();

        data.add( new ButtonData("-"));
        data.add( new ButtonData("7"));
        data.add( new ButtonData("8"));
        data.add( new ButtonData("9"));
        data.add( new ButtonData("CE"));

        data.add( new ButtonData("+"));
        data.add( new ButtonData("4"));
        data.add( new ButtonData("5"));
        data.add( new ButtonData("6"));
        data.add( new ButtonData("("));

        data.add( new ButtonData("/"));
        data.add( new ButtonData("1"));
        data.add( new ButtonData("2"));
        data.add( new ButtonData("3"));
        data.add( new ButtonData(")"));

        data.add( new ButtonData("*"));
        data.add( new ButtonData("0"));
        data.add( new ButtonData("00"));
        data.add( new ButtonData("."));
        data.add( new ButtonData("="));
        return data;
    }
}
