package com.jl426551.example.mycalculator;

public class Calculator {

    public static boolean VerifyString(String operation) {
        int count = 0;
        //Count opening and closing parenthesis
        for (int i = 0; i < operation.length(); i++) {
            if (operation.charAt(i) == '(')
                count++;
            else if (operation.charAt(i) == ')')
                count--;
        }

        //There is a mismatch of parenthesis
        if (count != 0)
            return false;
        else {
            for (int i = 0; i < operation.length() - 1; i++) {
                if (isNotANumber(operation.charAt(i))) {
                    //Back-to-back operators are not allowed, except for when - indicates a negative.
                    if (isNotANumber(operation.charAt(i + 1)) && (operation.charAt(i + 1) != '-'))
                        return false;
                }
            }

            return true;
        }
    }

    public static String getResult(String operation) {
        System.out.println("getResult called with " + operation);
        double totalValue;

        // * * * * * * * * Calculate exponents * * * * * * * *
        //Process parenthesis
        int closingIndex = -1;
        int openIndex = -1;

        String modifiedOperation = operation;
        do {
            //locate last opening parenthesis
            openIndex = modifiedOperation.lastIndexOf('(');
            //locase first closing parenthesis
            closingIndex = modifiedOperation.indexOf(')', openIndex);

            if (openIndex == -1 || closingIndex == -1) {
                System.out.println("No parenthesis found");
            } else {
                System.out.println("Open index at " + openIndex);
                System.out.println("Closing index at " + closingIndex);
                System.out.println("split: + \n");
                System.out.println(modifiedOperation.substring(0, openIndex));
                System.out.println(modifiedOperation.substring(closingIndex + 1, modifiedOperation.length()));

                modifiedOperation = modifiedOperation.substring(0, openIndex) + getResult(modifiedOperation.substring(openIndex + 1, closingIndex)) + modifiedOperation.substring(closingIndex + 1, modifiedOperation.length());
            }

        } while (openIndex != -1 && closingIndex != -1);

        // * * * * * * * * Calculate exponents * * * * * * * *


        //Compute Multiplication-Division
        boolean mdFound = true;
        while (mdFound) {
            int muliplicationIndex = modifiedOperation.indexOf('*');
            int divisionIndex = modifiedOperation.indexOf('/');

            //Neither multiplier nor division characters found.
            if (muliplicationIndex == -1 && divisionIndex == -1) {
                //System.out.println("Multiplication nor division found");
                mdFound = false;
            }
            //At least a multiplication or division character found.
            else if (muliplicationIndex == -1) {
                //COMPLETE DIVISION
                //System.out.println("Division only");
                modifiedOperation = CompleteDivision(modifiedOperation);
                //mdFound = false;
            } else if (divisionIndex == -1) {
                //COMPLETE MULTIPLICATION
                //System.out.println("Multiplication only");
                //mdFound = false;
                modifiedOperation = CompleteMultiplicaton(modifiedOperation);
            } else {
                //System.out.println("Both multiplication and division found");
                //mdFound = false;
                if (muliplicationIndex < divisionIndex)
                    modifiedOperation = CompleteMultiplicaton(modifiedOperation);
                else
                    modifiedOperation = CompleteDivision(modifiedOperation);
            }

        }

        //System.out.print("String after multiplication ");
        //System.out.println(modifiedOperation + "\n");


        //Complete addition substraction operations.
        boolean asFound = true;
        while (asFound) {
            int additionIndex = modifiedOperation.indexOf('+');
            int substractionIndex = modifiedOperation.indexOf('-');

            //String leads with a negative sign.
            if (substractionIndex == 0) {
                substractionIndex = modifiedOperation.substring(1, modifiedOperation.length()).indexOf('-');
                //    System.out.println("Negative index found at 0, modified to " + substractionIndex);
            }
            if (substractionIndex != -1)
                substractionIndex++;

            //Neither addition nor substraction characters found.
            if (additionIndex == -1 && substractionIndex == -1) {
                //    System.out.println("Addition nor substraction found");
                asFound = false;
            }
            //At least a multiplication or division character found.
            else if (additionIndex == -1) {
                //COMPLETE DIVISION
                //    System.out.println("substraction only");
                modifiedOperation = CompleteSubstraction(modifiedOperation);
                //mdFound = false;
            } else if (substractionIndex == -1) {
                //COMPLETE MULTIPLICATION
                //    System.out.println("Addition only");
                //mdFound = false;
                modifiedOperation = CompleteAddition(modifiedOperation);
            } else {
                //    System.out.println("Both addition and substraction found");
                //mdFound = false;
                if (additionIndex < substractionIndex)
                    modifiedOperation = CompleteAddition(modifiedOperation);
                else
                    modifiedOperation = CompleteSubstraction(modifiedOperation);
            }

        }

        //Return value
//        System.out.println("Returning " + modifiedOperation);
        return modifiedOperation;
    }

    public static String CompleteMultiplicaton(String computation) {
        System.out.println("cMultiply with " + computation);
        int mulIndex = computation.indexOf('*');

        //scan left to find first non-number character.
        int leftIndex = mulIndex - 1;
        boolean leftFound = false;

        while (leftIndex >= 0 && !leftFound) {
            if (!isNotANumber(computation.charAt(leftIndex))) {
                leftIndex--;
            } else
                leftFound = true;
        }

        //Negative value adjustments.
        //The very first character is a negative.
        if (leftIndex == 0 && computation.charAt(leftIndex) == '-')
            leftIndex = -1;
        else if (leftIndex > 0 && computation.charAt(leftIndex) == '-') {
            //Only check if the leftIndex of a non-number character is found.
            //If the character to its left is also a symbol add.
            if (isNotANumber(computation.charAt(leftIndex - 1)))
                leftIndex--;

        }

        double leftNumber = Double.parseDouble(computation.substring(leftIndex + 1, mulIndex));

        //scan right to find first non-number character.
        int rightIndex = mulIndex + 1;
        boolean rightFound = false;

        //Check for negative symbol first.
        //The number right after the multiplier is a nonNumber
        if (computation.charAt(rightIndex) == '-')
            rightIndex++;

        while (rightIndex < computation.length() && !rightFound) {
            if (!isNotANumber(computation.charAt(rightIndex))) {
                rightIndex++;
            } else
                rightFound = true;
        }

        double rightNumber = Double.parseDouble(computation.substring(mulIndex + 1, rightIndex));

        //System.out.println("Left number read as " + leftNumber);
        //System.out.println("Right number read as " + rightNumber);

        //System.out.println("Modifying result string");

        //Perform operation
        rightNumber *= leftNumber;

        //Break and re-assemble string
        String modifiedString = computation.substring(0, leftIndex + 1) + rightNumber + computation.substring(rightIndex, computation.length());
        //System.out.println("Newly modified string is   ");
        //System.out.print( modifiedString);

        return modifiedString;
    }

    public static String CompleteDivision(String computation) {
        System.out.println("cDivision with " + computation);
        int mulIndex = computation.indexOf('/');

        //scan left to find first non-number character.
        int leftIndex = mulIndex - 1;
        boolean leftFound = false;

        while (leftIndex >= 0 && !leftFound) {
            if (!isNotANumber(computation.charAt(leftIndex))) {
                leftIndex--;
            } else
                leftFound = true;
        }

        //Negative value adjustments.
        //        System.out.println("left index read at " + leftIndex);
        //        System.out.println("mul index " + mulIndex);
        //The very first character is a negative.
        if (leftIndex == 0 && computation.charAt(leftIndex) == '-')
            leftIndex = -1;
        else if (leftIndex > 0 && computation.charAt(leftIndex) == '-') {
            //Only check if the leftIndex of a non-number character is found.
            //If the character to its left is also a symbol add.
            if (isNotANumber(computation.charAt(leftIndex - 1)))
                leftIndex--;

        }

        double leftNumber = Double.parseDouble(computation.substring(leftIndex + 1, mulIndex));

        //scan right to find first non-number character.
        int rightIndex = mulIndex + 1;
        boolean rightFound = false;

        //Check for negative symbol first.
        //The number right after the multiplier is a nonNumber
        if (computation.charAt(rightIndex) == '-')
            rightIndex++;

        while (rightIndex < computation.length() && !rightFound) {
            if (!isNotANumber(computation.charAt(rightIndex))) {
                rightIndex++;
            } else
                rightFound = true;
        }

        double rightNumber = Double.parseDouble(computation.substring(mulIndex + 1, rightIndex));

        //System.out.println("Left number read as " + leftNumber);
        //System.out.println("Right number read as " + rightNumber);

        //System.out.println("Modifying result string");

        //Perform operation
        rightNumber = leftNumber / rightNumber;

        //Break and re-assemble string
        String modifiedString = computation.substring(0, leftIndex + 1) + rightNumber + computation.substring(rightIndex, computation.length());
        //System.out.println("Newly modified string is   ");
        //System.out.print( modifiedString);

        return modifiedString;
    }

    public static String CompleteAddition(String computation) {
        System.out.println("CAddiTION with " + computation);
        int mulIndex = computation.indexOf('+');

        //scan left to find first non-number character.
        int leftIndex = mulIndex - 1;
        boolean leftFound = false;

        while (leftIndex >= 0 && !leftFound) {
            if (!isNotANumber(computation.charAt(leftIndex))) {
                leftIndex--;
            } else
                leftFound = true;
        }

        //Negative value adjustments.
        //       System.out.println("left index read at " + leftIndex);
        //       System.out.println("mul index " + mulIndex);
        //The very first character is a negative.
        if (leftIndex == 0 && computation.charAt(leftIndex) == '-')
            leftIndex = -1;
        else if (leftIndex > 0 && computation.charAt(leftIndex) == '-') {
            //Only check if the leftIndex of a non-number character is found.
            //If the character to its left is also a symbol add.
            if (isNotANumber(computation.charAt(leftIndex - 1)))
                leftIndex--;

        }

        double leftNumber = Double.parseDouble(computation.substring(leftIndex + 1, mulIndex));

        //scan right to find first non-number character.
        int rightIndex = mulIndex + 1;
        boolean rightFound = false;

        //Check for negative symbol first.
        //The number right after the multiplier is a nonNumber
        if (computation.charAt(rightIndex) == '-')
            rightIndex++;

        while (rightIndex < computation.length() && !rightFound) {
            if (!isNotANumber(computation.charAt(rightIndex))) {
                rightIndex++;
            } else
                rightFound = true;
        }

        double rightNumber = Double.parseDouble(computation.substring(mulIndex + 1, rightIndex));

        //System.out.println("Left number read as " + leftNumber);
        //System.out.println("Right number read as " + rightNumber);

        //System.out.println("Modifying result string");

        //Perform operation
        rightNumber += leftNumber;

        //Break and re-assemble string
        String modifiedString = computation.substring(0, leftIndex + 1) + rightNumber + computation.substring(rightIndex, computation.length());
        //System.out.println("Newly modified string is   ");
        //System.out.print( modifiedString);

        return modifiedString;
    }

    public static String CompleteSubstraction(String computation) {
        System.out.println("Substraction with " + computation);
        int mulIndex = computation.indexOf('-');

        if (mulIndex == 0) {
            mulIndex = computation.substring(1, computation.length()).indexOf('-') + 1;
        }
        // System.out.println("mul index " + mulIndex + ". Starting scan");


        //scan left to find first non-number character.
        int leftIndex = mulIndex - 1;
        boolean leftFound = false;

        while (leftIndex >= 0 && !leftFound) {
            if (!isNotANumber(computation.charAt(leftIndex))) {
                leftIndex--;
            } else
                leftFound = true;
        }

        //Negative value adjustments.
        //System.out.println("left index read at " + leftIndex);
        //System.out.println("mul index " + mulIndex);

        //The very first character is a negative.
        if (leftIndex == 0 && computation.charAt(leftIndex) == '-')
            leftIndex = -1;
        else if (leftIndex > 0 && computation.charAt(leftIndex) == '-') {
            //Only check if the leftIndex of a non-number character is found.
            //If the character to its left is also a symbol add.
            if (isNotANumber(computation.charAt(leftIndex - 1)))
                leftIndex--;

        }

        double leftNumber = Double.parseDouble(computation.substring(leftIndex + 1, mulIndex));

        //scan right to find first non-number character.
        int rightIndex = mulIndex + 1;
        boolean rightFound = false;

        //Check for negative symbol first.
        //The number right after the multiplier is a nonNumber
        if (computation.charAt(rightIndex) == '-')
            rightIndex++;

        while (rightIndex < computation.length() && !rightFound) {
            if (!isNotANumber(computation.charAt(rightIndex))) {
                rightIndex++;
            } else
                rightFound = true;
        }

        double rightNumber = Double.parseDouble(computation.substring(mulIndex + 1, rightIndex));

        //System.out.println("Left number read as " + leftNumber);
        //System.out.println("Right number read as " + rightNumber);

        //System.out.println("Modifying result string");

        //Perform operation
        rightNumber = leftNumber - rightNumber;

        //Break and re-assemble string
        String modifiedString = computation.substring(0, leftIndex + 1) + rightNumber + computation.substring(rightIndex, computation.length());
        //System.out.println("Newly modified string is   ");
        //System.out.print( modifiedString);

        return modifiedString;
    }

    private static boolean isNotANumber(char character) {
        //System.out.println("Checking not a number for " + character);

        if (character == '/' || character == '+' || character == '*' || character == '-')
            return true;
        else return false;

    }
}
