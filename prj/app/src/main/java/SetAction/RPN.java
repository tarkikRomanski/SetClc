package SetAction;

import android.util.Log;

import java.util.SortedSet;
import java.util.Stack;

/**
 Reverse Polish Notation
 Обратная польская нотация
 Зворотня польська нотація
 */
public abstract class RPN {
    private static boolean isSet(String Symbol) {
        if(     Symbol == "A" ||
                Symbol == "B" ||
                Symbol == "C" ||
                Symbol == "D" ||
                Symbol == "F"
                )
            return true;
        else return false;
    }

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

    public static String convertToRPN(String expression) {
        char[] expressionArr = expression.toCharArray();
        String reverseExpression = "";
        Stack stack = new Stack();
        String log = "";
        for(int i=0;i<expressionArr.length;i++)
            log += expressionArr[i] + ";";
        Log.i("Bsdfsfs", log);
        int count = expressionArr.length;

        for (int i=1; i<count; i++) {
            if(isSet(expressionArr[i])) {
                reverseExpression += expressionArr[i];
            } else if(expressionArr[i] == '(') {
                stack.push(expressionArr[i]);
            } else if(expressionArr[i] == ')') {
                while (stack.peek() != '(') {
                    reverseExpression += stack.pop();
                }
                stack.pop();
            } else {
                if (!stack.empty()) {
                if((expressionArr[i] == '+' || expressionArr[i] =='/')  && stack.peek() == '*') {
                        reverseExpression += stack.pop();
                        stack.push(expressionArr[i]);
                    } else {
                    stack.push(expressionArr[i]);
                }
                } else {
                    stack.push(expressionArr[i]);
                }
            }
        }
        while (!stack.empty()) {
            reverseExpression += stack.pop();
        }
        Log.i("Bsdfsfs", reverseExpression);
        return reverseExpression;
    }

    public static SortedSet calculateRPN(String RPNexpression,
                                         SortedSet A,
                                         SortedSet B,
                                         SortedSet C,
                                         SortedSet D,
                                         SortedSet F) {
        char[] expressionArr = RPNexpression.toCharArray();
        int count = expressionArr.length;
        SortedSet firstSet;
        SortedSet secondSet;
        SortedSet resultSet;

        Stack<SortedSet> stack = new Stack();
        for (int i=0; i<count; i++){
            Log.i("Bsdfsfs", "Step " + i);
            if(isSet(expressionArr[i])) {
                switch(expressionArr[i]) {
                    case 'A': stack.push(A); break;
                    case 'B': stack.push(B); break;
                    case 'C': stack.push(C); break;
                    case 'D': stack.push(D); break;
                    case 'F': stack.push(F); break;
                    default: stack.push(null);
                }
                Log.i("Bsdfsfs", "pushing " + expressionArr[i]);
                Log.i("Bsdfsfs", expressionArr[i] + ":" + stack.peek().toString());
            } else {
                firstSet = stack.pop();
                secondSet = stack.pop();
                switch (expressionArr[i]) {
                    case '+': resultSet = ActionSets.unionMatrix(firstSet, secondSet, null); break;
                    case '/': resultSet = ActionSets.minusMatrix(secondSet, firstSet , null); break;
                    case '*': resultSet = ActionSets.crossingMatrix(firstSet, secondSet, null); break;
                    default: resultSet = null; break;
                }
                Log.i("Bsdfsfs", firstSet.toString() + expressionArr[i] + secondSet.toString() + " = " + resultSet.toString());
                stack.push(resultSet);
            }
        }
        return stack.pop();
    }
}
