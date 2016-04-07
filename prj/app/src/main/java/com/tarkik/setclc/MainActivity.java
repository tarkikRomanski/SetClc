package com.tarkik.setclc;

import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;

import SetAction.ActionSets;
import SetAction.CalculateExpression;
import SetAction.DrawingSets;
import SetAction.RPN;

import static android.view.View.GONE;

public class MainActivity extends FragmentActivity {

    private DrawingSets drawingSets = new DrawingSets();

    private Button matrixMode;
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

    private Button clickA;
    private Button clickB;
    private Button clickC;
    private Button clickD;
    private Button clickF;
    private Button clickPlus;
    private Button clickCross;
    private Button clickMinus;
    private Button calculate;
    private TextView expression;
    private Button clickOpen;
    private Button clickClose;
    private Button delete;

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


    private SortedSet fMatr = new TreeSet();
    private SortedSet sMatr = new TreeSet();
    private SortedSet resultArr = new TreeSet();
    private Iterator resultIter;

    private boolean justMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        matrixMode = (Button)findViewById(R.id.matrix_btn);
            //
        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        drawingSets.setDeviceWidth(p.x);
        drawingSets.setIvResult((ImageView) findViewById(R.id.result_diagram));
        drawingSets.setPaint(new Paint(Paint.ANTI_ALIAS_FLAG));
        drawingSets.setAccentColor(getResources().getColor(R.color.accent));
        drawingSets.setDarkprimaryColor(getResources().getColor(R.color.dark_primary));
        drawingSets.setPrimaryColor(getResources().getColor(R.color.primary));
        drawingSets.setTxtSize(getResources().getDimension(R.dimen.txt_size));
        //


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

        clickA = (Button)findViewById(R.id.clickA);
        clickB = (Button)findViewById(R.id.clickB);
        clickC = (Button)findViewById(R.id.clickC);
        clickD = (Button)findViewById(R.id.clickD);
        clickF = (Button)findViewById(R.id.clickF);
        clickPlus = (Button)findViewById(R.id.clickPlus);
        clickCross = (Button)findViewById(R.id.clickCross);
        clickMinus = (Button)findViewById(R.id.clickMinus);
        clickOpen = (Button)findViewById(R.id.clickOpen);
        clickClose = (Button)findViewById(R.id.clickClose);
        calculate = (Button)findViewById(R.id.calculate);
        expression = (TextView)findViewById(R.id.expression);
        delete = (Button)findViewById(R.id.delete);

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

        View.OnClickListener selectedMode = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                drawingSets.clearDrawing();
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

                if(matrixA.getText().toString().length() == 0) clickA.setVisibility(GONE); else clickA.setVisibility(View.VISIBLE);
                if(matrixB.getText().toString().length() == 0) clickB.setVisibility(GONE); else clickB.setVisibility(View.VISIBLE);
                if(matrixC.getText().toString().length() == 0) clickC.setVisibility(GONE); else clickC.setVisibility(View.VISIBLE);
                //clickCross.setVisibility(GONE);
                if(matrixD.getText().toString().length() == 0) clickD.setVisibility(GONE); else clickD.setVisibility(View.VISIBLE);
                if(matrixF.getText().toString().length() == 0) clickF.setVisibility(GONE); else clickF.setVisibility(View.VISIBLE);
                //calculate.setVisibility(GONE);
                //clickMinus.setVisibility(GONE);
                //clickPlus.setVisibility(GONE);

                /*if(justMode) {
                    justMode = false;
                    matrixMode.setText(getResources().getText(R.string.set));
                    if(matrixA.getText().toString().length() != 0) clickA.setVisibility(View.VISIBLE);
                    if(matrixB.getText().toString().length() != 0) clickB.setVisibility(View.VISIBLE);
                    if(matrixC.getText().toString().length() != 0) clickC.setVisibility(View.VISIBLE);
                    clickCross.setVisibility(View.VISIBLE);
                    if(matrixD.getText().toString().length() != 0) clickD.setVisibility(View.VISIBLE);
                    if(matrixF.getText().toString().length() != 0) clickF.setVisibility(View.VISIBLE);
                    calculate.setVisibility(View.VISIBLE);
                    clickMinus.setVisibility(View.VISIBLE);
                    clickPlus.setVisibility(View.VISIBLE);
                } else {
                    justMode = true;
                    matrixMode.setText(getResources().getText(R.string.complex_set));
                }*/
            }
        };

        View.OnClickListener clickDelete = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = "";
                char[] echar = expression.getText().toString().toCharArray();
                for(int i=0; i < echar.length-1; i++ )
                    e += echar[i];
                expression.setText(e);
            }
        };

        View.OnClickListener clickCalculator = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button thisButton = (Button) v;
                expression.setText(expression.getText() + "" + thisButton.getText());
            }
        };

        View.OnClickListener clickResultate = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strResult = "";
                SortedSet mA = new TreeSet();
                SortedSet mB = new TreeSet();
                SortedSet mC = new TreeSet();
                SortedSet mD = new TreeSet();
                SortedSet mF = new TreeSet();
                Log.i("D", "start");
                if (matrixA.getText().length() > 0)
                    for (int i=0; i < matrixA.getText().toString().split(" ").length; i++)
                        mA.add(matrixA.getText().toString().split(" ")[i]);
                if (matrixB.getText().length() > 0)
                    for (int i=0; i < matrixB.getText().toString().split(" ").length; i++)
                        mB.add(matrixB.getText().toString().split(" ")[i]);
                if (matrixC.getText().length() > 0)
                    for (int i=0; i < matrixC.getText().toString().split(" ").length; i++)
                        mC.add(matrixC.getText().toString().split(" ")[i]);
                if (matrixD.getText().length() > 0)
                    for (int i=0; i < matrixD.getText().toString().split(" ").length; i++)
                        mD.add(matrixD.getText().toString().split(" ")[i]);
                if (matrixF.getText().length() > 0)
                    for (int i=0; i < matrixF.getText().toString().split(" ").length; i++)
                        mF.add(matrixF.getText().toString().split(" ")[i]);
                resultArr.clear();
                RPN.convertToRPN(expression.getText().toString());
                resultArr = CalculateExpression.calculate(expression.getText().toString(), mA, mB, mC, mD, mF);
                Iterator complexIterator = resultArr.iterator();
                for (int i=0; i<resultArr.size(); i++) {
                    strResult += complexIterator.next() + " ";
                }

                text_res.setVisibility(View.VISIBLE);
                result.clearComposingText();
                result.setText(strResult);
                result.setVisibility(View.VISIBLE);
            }
        };

        View.OnClickListener clickFirstMatrix = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingSets.clearDrawing();
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
                drawingSets.clearDrawing();
                String strResult = "";
                switch(v.getId()) {
                    case R.id.plus:
                        minus.setBackgroundResource(R.color.primary);
                        multi.setBackgroundResource(R.color.primary);
                        resultArr = ActionSets.unionMatrix(fMatr, sMatr, drawingSets);
                        plus.setBackgroundResource(R.color.accent);
                        break;
                    case R.id.minus:
                        plus.setBackgroundResource(R.color.primary);
                        multi.setBackgroundResource(R.color.primary);
                        minus.setBackgroundResource(R.color.accent);
                        resultArr = ActionSets.minusMatrix(fMatr, sMatr, drawingSets);
                        break;
                    case R.id.multi:
                        plus.setBackgroundResource(R.color.primary);
                        minus.setBackgroundResource(R.color.primary);
                        multi.setBackgroundResource(R.color.accent);
                        resultArr = ActionSets.crossingMatrix(fMatr, sMatr, drawingSets);
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

        matrixMode.setOnClickListener(selectedMode);

        clickA.setOnClickListener(clickCalculator);
        clickB.setOnClickListener(clickCalculator);
        clickC.setOnClickListener(clickCalculator);
        clickD.setOnClickListener(clickCalculator);
        clickF.setOnClickListener(clickCalculator);
        clickPlus.setOnClickListener(clickCalculator);
        clickCross.setOnClickListener(clickCalculator);
        clickMinus.setOnClickListener(clickCalculator);
        clickOpen.setOnClickListener(clickCalculator);
        clickClose.setOnClickListener(clickCalculator);
        calculate.setOnClickListener(clickResultate);
        delete.setOnClickListener(clickDelete);


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
                    clickA.setVisibility(View.VISIBLE);
                } else {
                    bFA.setVisibility(GONE);
                    clickA.setVisibility(GONE);
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
                    clickB.setVisibility(View.VISIBLE);
                } else {
                    bFB.setVisibility(GONE);
                    clickB.setVisibility(GONE);
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
                    clickC.setVisibility(View.VISIBLE);
                } else {
                    bFC.setVisibility(GONE);
                    clickC.setVisibility(GONE);
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
                    clickD.setVisibility(View.VISIBLE);
                } else {
                    bFD.setVisibility(GONE);
                    clickD.setVisibility(GONE);
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
                    clickF.setVisibility(View.VISIBLE);
                } else {
                    bFF.setVisibility(GONE);
                    clickF.setVisibility(GONE);
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


}
