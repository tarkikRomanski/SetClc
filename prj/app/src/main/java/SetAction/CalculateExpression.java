package SetAction;

import android.util.Log;

import java.util.SortedSet;

public abstract class CalculateExpression {

    private static boolean isSet(char Symbol) {
        if(     Symbol == 'A' ||
                Symbol == 'B' ||
                Symbol == 'C' ||
                Symbol == 'D' ||
                Symbol == 'F'
                )
            return true;
        else return false;
    }

    public static SortedSet calculate(String expression, SortedSet A, SortedSet B, SortedSet C, SortedSet D, SortedSet F ) {
        SortedSet result = null;


        result = RPN.calculateRPN(RPN.convertToRPN(expression), A, B, C, D, F);
        Log.i("Bsdfsfs", "Start calculate reverse expression: " + result.toString());
        return result;
    }
}
