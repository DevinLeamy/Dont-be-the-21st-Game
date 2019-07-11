package com.example.myfirstgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//Add Values to Buttons and Output Method
    int counter = 0;
    public void addValue(Button btn1, Button btn2, Button btn3, TextView output, int increment)
    {
        int outputText = Integer.parseInt(output.getText().toString());

        int[] buttonValues = {
                Integer.parseInt(btn1.getText().toString()),
                Integer.parseInt(btn2.getText().toString()),
                Integer.parseInt(btn3.getText().toString()),
        };
        Button[] buttons = { btn1, btn2, btn3 };
        for (int i = 0; i < buttons.length ; i++)
        {
            if (buttonValues[i] + increment <= 21)
            {
                buttons[i].setText(buttonValues[i] + increment + "");
            } else {
                buttons[i].setText(21 + "");
            }
        }
        if (outputText + increment <= 21) {output.setText((outputText + increment) + "");}
        else {output.setText(21 + "");}
    }
    public void setVisibility(Button btn, String visibility)
    {
        if (visibility.equals("invisible"))
        {
            btn.setVisibility(View.GONE);
            btn.setEnabled(false);
        } else if (visibility.equals("visible"))
        {
            btn.setVisibility(View.VISIBLE);
            btn.setEnabled(true);
        }
    }
//Set Button Colors
    public void setColor(Button btn1, Button btn2, Button btn3, String color)
    {
        for (Button btn: new Button[] {btn1, btn2, btn3}) {
            if (color == "Blue") {
                btn.setBackgroundColor(getResources().getColor(R.color.DefaultBlue));
            } else if (color == "Red")
            {
                btn.setBackgroundColor(getResources().getColor(R.color.DefaultRed));
            } else if (color == "White")
            {
                btn.setBackgroundColor(getResources().getColor(R.color.DefaultWhite));
            }
        }
    }
    public void gameState(Button btn1, Button btn2, Button btn3, Button random, Button smart, String desiredState, int counter)
    {
        Button[] buttons = {
                btn1,
                btn2,
                btn3
        };
        switch (desiredState){
            case "medium":
                smart.setEnabled(false);
                smart.setVisibility(View.GONE);
                if (counter % 2 == 1)
                {
                    for (Button btn: buttons) {
                        btn.setBackgroundColor(getResources().getColor(R.color.DefaultWhite));
                        btn.setEnabled(false);
                    }
                    random.setEnabled(true);
                    random.setBackgroundColor(getResources().getColor(R.color.DefaultRed));
                } else if (counter % 2 == 0)
                {
                    for (Button btn: buttons) {
                        btn.setBackgroundColor(getResources().getColor(R.color.DefaultBlue));
                        btn.setEnabled(true);
                    }
                    random.setEnabled(false);
                    random.setBackgroundColor(getResources().getColor(R.color.DefaultWhite));
                }
                break;
            case "hard":
                random.setEnabled(false);
                random.setVisibility(View.GONE);
                if (counter % 2 == 1)
                {
                    for (Button btn: buttons) {
                        btn.setBackgroundColor(getResources().getColor(R.color.DefaultWhite));
                        btn.setEnabled(false);
                    }
                    smart.setEnabled(true);
                    smart.setBackgroundColor(getResources().getColor(R.color.DefaultRed));
                } else if (counter % 2 == 0)
                {
                    for (Button btn: buttons) {
                        btn.setBackgroundColor(getResources().getColor(R.color.DefaultBlue));
                        btn.setEnabled(true);
                    }
                    smart.setEnabled(false);
                    smart.setBackgroundColor(getResources().getColor(R.color.DefaultWhite));
                }
                break;
            case "none":
                smart.setEnabled(false);
                smart.setVisibility(View.GONE);
                random.setEnabled(false);
                random.setVisibility(View.GONE);
                if (counter % 2 == 1)
                {
                    for (Button btn: buttons) {
                        btn.setBackgroundColor(getResources().getColor(R.color.DefaultRed));
                        btn.setEnabled(true);
                    }
                } else if (counter % 2 == 0)
                {
                    for (Button btn: buttons) {
                        btn.setBackgroundColor(getResources().getColor(R.color.DefaultBlue));
                        btn.setEnabled(true);
                    }
                }
                 break;
    }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Game Screen Buttons
        final Button addOneBtn = findViewById(R.id.addOneBtn);
        final Button addTwoBtn = findViewById(R.id.addTwoBtn);
        final Button addThreeBtn = findViewById(R.id.addThreeBtn);
        final Button randomBtn = findViewById(R.id.randomBtn);
        final Button smartBtn = findViewById(R.id.smartBtn);
//Game Screen TextView
        final TextView output = findViewById(R.id.outputTextView);
//Game Screen Intent
        final Intent startIntent = new Intent(getApplicationContext(), RestartPage.class);
        final Button[] buttons = {
                addOneBtn,
                addTwoBtn,
                addThreeBtn
        };
        final int[] buttonValues = {
                Integer.parseInt(addOneBtn.getText().toString()),
                Integer.parseInt(addOneBtn.getText().toString()),
                Integer.parseInt(addOneBtn.getText().toString()),
                Integer.parseInt(output.getText().toString())
        };

//Smart Button
        smartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                Button[] buttons = {
                        addOneBtn,
                        addTwoBtn,
                        addThreeBtn
                };
                if (output.getText().toString().equals("20")){
                    startIntent.putExtra("youWin", "You Win!");
                    startActivity(startIntent);
                }
                int[] values = {1, 2, 3};
                ArrayList<Integer> keyValues = new ArrayList<>();
                keyValues.add(4); keyValues.add(8); keyValues.add(12); keyValues.add(16);
                keyValues.add(20);
                if (buttonValues[3] == 20)
                {
                    startIntent.putExtra("youWin", "You Win!");
                    startActivity(startIntent);
                }
                for (int i = 0; i < buttons.length; i++)
                {
                    int buttonValue = Integer.parseInt(buttons[i].getText().toString());
                    if (keyValues.contains(buttonValue))
                    {
                        addValue(addOneBtn, addTwoBtn, addThreeBtn, output, values[i]);
                        break;
                    }
                    if (i == 2) {
                        addValue(addOneBtn, addTwoBtn, addThreeBtn, output, 1);
                    }
                }
                gameState(addOneBtn, addTwoBtn, addThreeBtn, randomBtn, smartBtn, getIntent().getExtras().getString("howChallenging"), counter);
            }
        });
//Add One Button
        addOneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                counter++;
                addValue(addOneBtn, addTwoBtn, addThreeBtn, output, 1);
                if (output.getText().toString().equals("21")) {
                    startIntent.putExtra("youLose", "You Lose...");
                    startActivity(startIntent);
                }
                gameState(addOneBtn, addTwoBtn, addThreeBtn, randomBtn, smartBtn, getIntent().getExtras().getString("howChallenging"), counter);
            }
        });
//Add Two Button
        addTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                addValue(addOneBtn, addTwoBtn, addThreeBtn, output, 2);
                if (output.getText().toString().equals("21")) {
                    startIntent.putExtra("youLose", "You Lose...");
                    startActivity(startIntent);
                }
                gameState(addOneBtn, addTwoBtn, addThreeBtn, randomBtn, smartBtn, getIntent().getExtras().getString("howChallenging"), counter);

            }
        });
//Add Three Button
        addThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                addValue(addOneBtn, addTwoBtn, addThreeBtn, output, 3);
                if (output.getText().toString().equals("21")) {
                    startIntent.putExtra("youLose", "You Lose...");
                    startActivity(startIntent);
                }
                gameState(addOneBtn, addTwoBtn, addThreeBtn, randomBtn, smartBtn, getIntent().getExtras().getString("howChallenging"), counter);

            }
        });
//Random Increment Button
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                ArrayList<Integer> buttonValues = new ArrayList<>();
                buttonValues.add(Integer.parseInt(addOneBtn.getText().toString()));
                buttonValues.add(Integer.parseInt(addTwoBtn.getText().toString()));
                buttonValues.add(Integer.parseInt(addThreeBtn.getText().toString()));

                if (output.getText().toString().equals("20")){
                    startIntent.putExtra("youWin", "You Win!");
                    startActivity(startIntent);
                }
                if (buttonValues.contains(20)) {
                    for (int i = 1; i < 4; i++) {
                        if (buttonValues.get(i - 1) == 20) {
                            addValue(addOneBtn, addTwoBtn, addThreeBtn, output, i);
                        }
                    }
                } else {
                    Random generateRandom = new Random();
                    int randomInt = generateRandom.nextInt((3 - 1) + 1) + 1;
                    if (Integer.parseInt(output.getText().toString()) + randomInt >= 21) {
                        startIntent.putExtra("youWin", "You Win!");
                        startActivity(startIntent);
                    }
                    addValue(addOneBtn, addTwoBtn, addThreeBtn, output, randomInt);
                }
                gameState(addOneBtn, addTwoBtn, addThreeBtn, randomBtn, smartBtn, getIntent().getExtras().getString("howChallenging"), counter);
            }
        });
//Resets Values
        if (getIntent().hasExtra("buttonValues")) {
            for (int i = 1; i < buttons.length + 1; i++) {
                buttons[i-1].setText(i + "");
            }
            output.setText(0 + "");
            for (Button btn: buttons) {
                if (!btn.isActivated()) {
                    btn.setActivated(true);
                }
                if (btn.getVisibility() == View.GONE)
                {
                    btn.setVisibility(View.VISIBLE);
                }
            }
        }
//Disables Buttons
        if (getIntent().hasExtra("howChallenging"))
        {
            String level = getIntent().getExtras().getString("howChallenging");
            if (level.equals("none"))
            {
                setVisibility(randomBtn, "invisible");
                setVisibility(smartBtn, "invisible");
                setColor(addOneBtn, addTwoBtn, addThreeBtn, "Blue");
            } else if (level.equals("medium"))
            {
                randomBtn.setEnabled(false);
                setVisibility(smartBtn, "invisible");
                setColor(addOneBtn, addTwoBtn, addThreeBtn, "Blue");
            } else if (level.equals("hard")){
                smartBtn.setEnabled(false);
                setVisibility(randomBtn, "invisible");
                setColor(addOneBtn, addTwoBtn, addThreeBtn, "Blue");
            }
        }

    }
}
