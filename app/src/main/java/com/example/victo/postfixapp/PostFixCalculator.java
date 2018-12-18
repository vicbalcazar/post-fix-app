package com.example.victo.postfixapp;

import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class PostFixCalculator extends AppCompatActivity
        implements OnClickListener {

    //textViews: show number to be added to stack, the four top elements of stack, and result of operation
    private TextView numberTextView;
    private TextView stackTextView;
    private TextView resultTextView;
    //Number Buttons 0-9, total of 10 buttons
    private Button oneButton;
    private Button twoButton;
    private Button threeButton;
    private Button fourButton;
    private Button fiveButton;
    private Button sixButton;
    private Button sevenButton;
    private Button eightButton;
    private Button nineButton;
    private Button zeroButton;
    //decimal point button, 1 button
    private Button dotButton;
    //operator buttons +, -, *, /. Total of 4 buttons
    private Button plusButton;
    private Button minusButton;
    private Button multiplicationButton;
    private Button divisionButton;
    //Special operation buttons: Enter, Drop, Delete
    private Button enterButton;
    private Button dropButton;
    private Button deleteButton;


    //TESTING SOME CODE HERE
    private String tempString = "";
    private java.util.Deque<Float> myStack = new LinkedList<Float>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_fix_calculator);

        //initialize textViews x3
        numberTextView = (TextView) findViewById(R.id.numberTextView);
        stackTextView = (TextView) findViewById(R.id.stackTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        //initialize 0-9 buttons x10
        oneButton = (Button) findViewById(R.id.oneButton);
        twoButton =(Button) findViewById(R.id.twoButton);
        threeButton = (Button) findViewById(R.id.threeButton);
        fourButton = (Button) findViewById(R.id.fourButton);
        fiveButton = (Button) findViewById(R.id.fiveButton);
        sixButton = (Button) findViewById(R.id.sixButton);
        sevenButton = (Button) findViewById(R.id.sevenButton);
        eightButton = (Button) findViewById(R.id.eightButton);
        nineButton = (Button) findViewById(R.id.nineButton);
        zeroButton = (Button) findViewById(R.id.zeroButton);
        //initialize operator buttons, x4
        plusButton = (Button) findViewById(R.id.plusButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        multiplicationButton = (Button) findViewById(R.id.multiplicationButton);
        divisionButton = (Button) findViewById(R.id.divisionButton);
        //dot button x1
        dotButton = (Button) findViewById(R.id.dotButton);
        //Special orientation Buttons, x3
        enterButton = (Button) findViewById(R.id.enterButton);
        dropButton = (Button) findViewById(R.id.dropButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        //set listeners
        //number Buttons 0-9 listener
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        //dot button listener
        dotButton.setOnClickListener(this);
        //Special Buttons x 3 listeners
        deleteButton.setOnClickListener(this);
        enterButton.setOnClickListener(this);
        dropButton.setOnClickListener(this);
        //operator buttons x 4 listener
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        multiplicationButton.setOnClickListener(this);
        divisionButton.setOnClickListener(this);


    }

    public void addNumber(String value){
        if (value != ".") {
            tempString += value;
            numberTextView.setText(tempString);
        }
        else if(value == "." && !tempString.contains(".")){
            tempString += value;
            numberTextView.setText(tempString);

        }

    }

    public void deleteNumber(){
        if(tempString.length() > 0  ){
            tempString = tempString.substring(0, tempString.length() - 1);
            numberTextView.setText(tempString);
        }
        if (tempString.length() <= 0){
            tempString = "";
            numberTextView.setText("0");
        }

    }

    public void printStack(){
        String s = "", next = "";
        Iterator<Float> it = myStack.iterator();
        int i = 0;

        while(it.hasNext() && i < 4){
            next = new DecimalFormat("#######.###").format(it.next());
            //next = Float.toString(it.next());
            //s = next + ", " + s;
            s += next + ", ";

            i++;
        }

        stackTextView.setText(s);


    }

    public void toStack(){
        String s = numberTextView.getText().toString();
        myStack.push(Float.parseFloat(s));
        //stackTextView.setText(new DecimalFormat("#######.###").format(myStack.peek()));

        printStack();
        tempString = "";
        numberTextView.setText("0");

    }

    public void dropFromStack() {
        if (myStack.size() > 0) {
            myStack.pop();
            printStack();
            if (myStack.size() == 0)
                stackTextView.setText("EMPTY");
        }
        else {
            stackTextView.setText("EMPTY");
        }
    }

    public void postFixOp(String operator){
        if (myStack.size() > 1) {
            float n1 = myStack.pop();
            float n2 = myStack.pop();
            float result;

            switch (operator) {
                case "+":
                    result = n2 + n1;
                    myStack.push(result);
                    printStack();
                    resultTextView.setText(new DecimalFormat("#######.###").format(result));
                    break;
                case "-":
                    result = n2 - n1;
                    myStack.push(result);
                    printStack();
                    resultTextView.setText(new DecimalFormat("#######.###").format(result));
                    break;
                case "*":
                    result = n2 * n1;
                    myStack.push(result);
                    printStack();
                    resultTextView.setText(new DecimalFormat("#######.###").format(result));
                    break;
                case "/":
                    if (n1 != 0) {
                        result = n2 / n1;
                        myStack.push(result);
                        printStack();
                        resultTextView.setText(new DecimalFormat("#######.###").format(result));
                    }
                    else{
                        result = 0;
                        myStack.push(result);
                        printStack();
                        resultTextView.setText(new DecimalFormat("#######.###").format(result));
                    }
                    break;

            }
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.oneButton:
                addNumber((String)oneButton.getText());
                break;
            case R.id.twoButton:
                addNumber("2");
                break;
            case R.id.threeButton:
                addNumber("3");
                break;
            case R.id.fourButton:
                addNumber("4");
                break;
            case R.id.fiveButton:
                addNumber("5");
                break;
            case R.id.sixButton:
                addNumber("6");
                break;
            case R.id.sevenButton:
                addNumber("7");
                break;
            case R.id.eightButton:
                addNumber("8");
                break;
            case R.id.nineButton:
                addNumber("9");
                break;
            case R.id.zeroButton:
                addNumber("0");
                break;
            case R.id.dotButton:
                addNumber(".");
                break;
            case R.id.deleteButton:
                deleteNumber();
                break;
            case R.id.enterButton:
                toStack();
                break;
            case R.id.dropButton:
                dropFromStack();
                break;
            case R.id.plusButton:
                postFixOp("+");
                break;
            case R.id.minusButton:
                postFixOp("-");
                break;
            case R.id.multiplicationButton:
                postFixOp("*");
                break;
            case R.id.divisionButton:
                postFixOp("/");
                break;

        }
    }
}
