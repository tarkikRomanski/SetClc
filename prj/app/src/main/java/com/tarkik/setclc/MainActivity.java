package com.tarkik.setclc;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;

import static android.view.View.GONE;

public class MainActivity extends FragmentActivity {

    private EditText matrixA;
    private EditText matrixB;
    private EditText matrixD;
    private EditText matrixC;
    private EditText matrixF;

    private Button bFA;
    private Button bFB;
    private Button bFC;
    private Button bFD;
    private Button bFF;

    private Button plus;
    private Button minus;
    private Button multi;

    public TextView text_res;

    public TextView result;

    private Button bSA;
    private Button bSB;
    private Button bSC;
    private Button bSD;
    private Button bSF;

    private View firstBTN = null;
    private View secondBTN = null;

    private ArrayList fMatr = new ArrayList();
    private ArrayList sMatr = new ArrayList();
    private SortedSet resultArr = new TreeSet();
    private Iterator resultIter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        matrixA = (EditText)findViewById(R.id.matrixA);
        matrixB = (EditText)findViewById(R.id.matrixB);
        matrixC = (EditText)findViewById(R.id.matrixC);
        matrixD = (EditText)findViewById(R.id.matrixD);
        matrixF = (EditText)findViewById(R.id.matrixF);

        bFA = (Button)findViewById(R.id.FA);
        bFB = (Button)findViewById(R.id.FB);
        bFC = (Button)findViewById(R.id.FC);
        bFD = (Button)findViewById(R.id.FD);
        bFF = (Button)findViewById(R.id.FF);

        bSA = (Button)findViewById(R.id.SA);
        bSB = (Button)findViewById(R.id.SB);
        bSC = (Button)findViewById(R.id.SC);
        bSD = (Button)findViewById(R.id.SD);
        bSF = (Button)findViewById(R.id.SF);

        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        multi = (Button)findViewById(R.id.multi);

        result = (TextView)findViewById(R.id.result);
        text_res = (TextView)findViewById(R.id.textView6);

        if(matrixA.getText().toString().length() == 0) bFA.setVisibility(GONE); else bFA.setVisibility(View.VISIBLE);
        if(matrixB.getText().toString().length() == 0) bFB.setVisibility(GONE); else bFB.setVisibility(View.VISIBLE);
        if(matrixC.getText().toString().length() == 0) bFC.setVisibility(GONE); else bFC.setVisibility(View.VISIBLE);
        if(matrixD.getText().toString().length() == 0) bFD.setVisibility(GONE); else bFD.setVisibility(View.VISIBLE);
        if(matrixF.getText().toString().length() == 0) bFF.setVisibility(GONE); else bFF.setVisibility(View.VISIBLE);

        minus.setVisibility(GONE);
        plus.setVisibility(GONE);
        multi.setVisibility(GONE);
        result.setVisibility(GONE);
        text_res.setVisibility(GONE);

        bSA.setVisibility(GONE);
        bSB.setVisibility(GONE);
        bSC.setVisibility(GONE);
        bSD.setVisibility(GONE);
        bSF.setVisibility(GONE);

        View.OnClickListener clickFirstMatrix = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus.setVisibility(GONE);
                plus.setVisibility(GONE);
                multi.setVisibility(GONE);
                result.setVisibility(GONE);
                text_res.setVisibility(GONE);
                firstBTN = v;

                bFA.setBackgroundResource(R.color.primary);
                bFB.setBackgroundResource(R.color.primary);
                bFC.setBackgroundResource(R.color.primary);
                bFD.setBackgroundResource(R.color.primary);
                bFF.setBackgroundResource(R.color.primary);
                bSA.setBackgroundResource(R.color.primary);
                bSB.setBackgroundResource(R.color.primary);
                bSC.setBackgroundResource(R.color.primary);
                bSD.setBackgroundResource(R.color.primary);
                bSF.setBackgroundResource(R.color.primary);

                v.setBackgroundResource(R.color.accent);

                visiblitySecondBtn();

            }
        };

        View.OnClickListener clickSecondMatrix = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondBTN = v;


                bSA.setBackgroundResource(R.color.primary);
                bSB.setBackgroundResource(R.color.primary);
                bSC.setBackgroundResource(R.color.primary);
                bSD.setBackgroundResource(R.color.primary);
                bSF.setBackgroundResource(R.color.primary);

                v.setBackgroundResource(R.color.accent);

                minus.setVisibility(View.VISIBLE);
                plus.setVisibility(View.VISIBLE);
                multi.setVisibility(View.VISIBLE);
                minus.setBackgroundResource(R.color.primary);
                plus.setBackgroundResource(R.color.primary);
                multi.setBackgroundResource(R.color.primary);
            }
        };

        View.OnClickListener go = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fMatr.clear();
                sMatr.clear();
                int lengthMatr;
                switch(firstBTN.getId()) {
                    case R.id.FA:
                        lengthMatr = matrixA.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            fMatr.add(matrixA.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.FB:
                        lengthMatr = matrixB.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            fMatr.add(matrixB.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.FC:
                        lengthMatr = matrixC.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            fMatr.add(matrixC.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.FD:
                        lengthMatr = matrixD.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            fMatr.add(matrixD.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.FF:
                        lengthMatr = matrixF.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            fMatr.add(matrixF.getText().toString().split(" ")[i]);
                        }
                        break;
                }

                switch(secondBTN.getId()) {
                    case R.id.SA:
                        lengthMatr = matrixA.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            sMatr.add(matrixA.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.SB:
                        lengthMatr = matrixB.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            sMatr.add(matrixB.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.SC:
                        lengthMatr = matrixC.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            sMatr.add(matrixC.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.SD:
                        lengthMatr = matrixD.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            sMatr.add(matrixD.getText().toString().split(" ")[i]);
                        }
                        break;
                    case R.id.SF:
                        lengthMatr = matrixF.getText().toString().split(" ").length;

                        for(int i=0; i < lengthMatr;i++) {
                            sMatr.add(matrixF.getText().toString().split(" ")[i]);
                        }
                        break;
                }

                resultArr.clear();
                String strResult = "";
                switch(v.getId()) {
                    case R.id.plus:
                        minus.setBackgroundResource(R.color.primary);
                        multi.setBackgroundResource(R.color.primary);
                        resultArr = unionMatrix(fMatr, sMatr);
                        plus.setBackgroundResource(R.color.accent);
                        break;
                    case R.id.minus:
                        plus.setBackgroundResource(R.color.primary);
                        multi.setBackgroundResource(R.color.primary);
                        minus.setBackgroundResource(R.color.accent);
                        resultArr = minusMatrix(fMatr, sMatr);
                        break;
                    case R.id.multi:
                        plus.setBackgroundResource(R.color.primary);
                        minus.setBackgroundResource(R.color.primary);
                        multi.setBackgroundResource(R.color.accent);
                        resultArr = crossingMatrix(fMatr, sMatr);
                        break;
                }
                resultIter = resultArr.iterator();
                for (int i=0; i<resultArr.size(); i++) {
                    strResult += resultIter.next() + " ";
                }

                text_res.setVisibility(View.VISIBLE);
                result.clearComposingText();
                result.setText(strResult);
                result.setVisibility(View.VISIBLE);
            }
        };

        minus.setOnClickListener(go);
        plus.setOnClickListener(go);
        multi.setOnClickListener(go);

        bFA.setOnClickListener(clickFirstMatrix);
        bFB.setOnClickListener(clickFirstMatrix);
        bFC.setOnClickListener(clickFirstMatrix);
        bFD.setOnClickListener(clickFirstMatrix);
        bFF.setOnClickListener(clickFirstMatrix);

        bSA.setOnClickListener(clickSecondMatrix);
        bSB.setOnClickListener(clickSecondMatrix);
        bSC.setOnClickListener(clickSecondMatrix);
        bSD.setOnClickListener(clickSecondMatrix);
        bSF.setOnClickListener(clickSecondMatrix);

        matrixA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (matrixA.getText().toString().length() > 0) {
                    bFA.setVisibility(View.VISIBLE);
                } else {
                    bFA.setVisibility(GONE);
                }
                visiblitySecondBtn();
            }
        });

        matrixB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (matrixB.getText().toString().length() > 0) {
                    bFB.setVisibility(View.VISIBLE);
                } else {
                    bFB.setVisibility(GONE);
                }
                visiblitySecondBtn();
            }
        });

        matrixC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (matrixC.getText().toString().length() > 0) {
                    bFC.setVisibility(View.VISIBLE);
                } else {
                    bFC.setVisibility(GONE);
                }
                visiblitySecondBtn();
            }
        });

        matrixD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (matrixD.getText().toString().length() > 0) {
                    bFD.setVisibility(View.VISIBLE);
                } else {
                    bFD.setVisibility(GONE);
                }
                visiblitySecondBtn();
            }
        });

        matrixF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (matrixF.getText().toString().length() > 0) {
                    bFF.setVisibility(View.VISIBLE);
                } else {
                    bFF.setVisibility(GONE);
                }
                visiblitySecondBtn();
            }
        });
    }

    private boolean visiblitySecondBtn() {
        try {
            if (matrixA.getText().toString().length() == 0) bSA.setVisibility(GONE);
            else if (firstBTN != null && firstBTN.getId() != R.id.FA) bSA.setVisibility(View.VISIBLE);
            else bSA.setVisibility(GONE);
            if (matrixB.getText().toString().length() == 0) bSB.setVisibility(GONE);
            else if (firstBTN != null && firstBTN.getId() != R.id.FB) bSB.setVisibility(View.VISIBLE);
            else bSB.setVisibility(GONE);
            if (matrixC.getText().toString().length() == 0) bSC.setVisibility(GONE);
            else if (firstBTN != null && firstBTN.getId() != R.id.FC) bSC.setVisibility(View.VISIBLE);
            else bSC.setVisibility(GONE);
            if (matrixD.getText().toString().length() == 0) bSD.setVisibility(GONE);
            else if (firstBTN != null && firstBTN.getId() != R.id.FD) bSD.setVisibility(View.VISIBLE);
            else bSD.setVisibility(GONE);
            if (matrixF.getText().toString().length() == 0) bSF.setVisibility(GONE);
            else if (firstBTN != null && firstBTN.getId() != R.id.FF) bSF.setVisibility(View.VISIBLE);
            else bSF.setVisibility(GONE);

            return true;
        } catch (ArithmeticException e) {
            return false;
        }
    }

    private SortedSet unionMatrix(ArrayList firstMatrix, ArrayList secondMatrix){
        SortedSet unionM = new TreeSet();
        for (int i=0; i < firstMatrix.size(); i++) {
            unionM.add(firstMatrix.get(i));
        }
        for (int j=0; j < secondMatrix.size(); j++){
            for (int i=0; i < firstMatrix.size(); i++)
                if (firstMatrix.get(i) != secondMatrix.get(j)) {
                    unionM.add(secondMatrix.get(j));
                    break;
                }
        }

        return unionM;
    }

    private SortedSet crossingMatrix(ArrayList firstMarix, ArrayList secondMatrix){
        SortedSet crossingM = new TreeSet();

        int lengthFirst = firstMarix.size();
        int lengthSecond = secondMatrix.size();

        for (int i=0; i < lengthFirst; i++) {
            for (int j=0; j<lengthSecond; j++) {
                if(firstMarix.get(i).equals(secondMatrix.get(j))) {
                    crossingM.add(firstMarix.get(i));
                    break;
                }
            }
        }

        return crossingM;
    }

    private SortedSet minusMatrix(ArrayList firstMatrix, ArrayList secondMatrix){
        SortedSet minusM = new TreeSet();
        boolean getThisValue;
        for (int i=0; i<firstMatrix.size(); i++) {
            getThisValue = true;
            for (int j = 0; j < secondMatrix.size(); j++) {
                if (firstMatrix.get(i).equals(secondMatrix.get(j))) {
                    getThisValue = false;
                    break;
                }
            }
            if(getThisValue)
                minusM.add(firstMatrix.get(i));
        }
        return minusM;
    }


}
