package SetAction;


import android.util.Log;

import java.util.SortedSet;
import java.util.TreeSet;

public abstract class ActionSets {

    public static SortedSet unionMatrix(SortedSet firstMatrix, SortedSet secondMatrix, DrawingSets drawingSets){
        SortedSet unionM = new TreeSet();

        Log.i("Bsdfsfs", "Start union");
        Log.i("Bsdfsfs", firstMatrix.toString());
        Log.i("Bsdfsfs", secondMatrix.toString());

        for (int i=0; i < firstMatrix.size(); i++) {
                unionM.add(firstMatrix.toArray()[i].toString());
            Log.i("Bsdfsfs", "Union add " + firstMatrix.toArray()[i].toString());
        }

        int unionCount = unionM.size();

        for (int i=0; i < unionCount; i++) {
            for (int j = 0; j < secondMatrix.size(); j++)
                if (unionM.toArray()[i] != secondMatrix.toArray()[j]) {
                    Log.i("Bsdfsfs", unionM.toArray()[i] + " != " + secondMatrix.toArray()[j].toString());
                    unionM.add(secondMatrix.toArray()[j].toString());
                    Log.i("Bsdfsfs", "Union add " + secondMatrix.toArray()[j].toString());
                } else
                Log.i("Bsdfsfs", unionM.toArray()[i] + " == " + secondMatrix.toArray()[j].toString());
        }
        if(drawingSets != null)
            drawingSets.drawUnion();
        return unionM;
    }


    public static SortedSet crossingMatrix(SortedSet firstMarix, SortedSet secondMatrix, DrawingSets drawingSets){
        SortedSet crossingM = new TreeSet();

        int lengthFirst = firstMarix.size();
        int lengthSecond = secondMatrix.size();

        for (int i=0; i < lengthFirst; i++) {
            for (int j=0; j<lengthSecond; j++) {
                if(firstMarix.toArray()[i].equals(secondMatrix.toArray()[j])) {
                    crossingM.add(firstMarix.toArray()[i].toString());
                    break;
                }
            }
        }
        if(drawingSets != null) {
            if (crossingM.size() > 0)
                drawingSets.drawCrossing();
            else drawingSets.drawFull();
        }
        return crossingM;
    }

    public static SortedSet minusMatrix(SortedSet firstMatrix, SortedSet secondMatrix, DrawingSets drawingSets){
        SortedSet minusM = new TreeSet();
        boolean getThisValue;
        for (int i=0; i<firstMatrix.size(); i++) {
            getThisValue = true;
            for (int j = 0; j < secondMatrix.size(); j++) {
                if (firstMatrix.toArray()[i].equals(secondMatrix.toArray()[j])) {
                    getThisValue = false;
                    break;
                }
            }
            if(getThisValue)
                minusM.add(firstMatrix.toArray()[i].toString());
        }
        if(drawingSets != null)
            drawingSets.drawMinus();
        return minusM;
    }


}
