package com.example.myfirstgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RestartPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_page);

//Retry Page Buttons
        Button retryBtn = findViewById(R.id.retryBtn);
        TextView outcome = findViewById(R.id.outcomeTextView);
//Sets Color and TextView Based on the Games Outcome
        if (getIntent().hasExtra("youWin"))
        {
            outcome.setText(getIntent().getExtras().getString("youWin"));
            retryBtn.setBackgroundColor(getResources().getColor(R.color.DefaultBlue));
        } else if (getIntent().hasExtra("youLose"))
        {
            outcome.setText(getIntent().getExtras().getString("youLose"));
            retryBtn.setBackgroundColor(getResources().getColor(R.color.DefaultRed));
        }
//Retry Button
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), LaunchPage.class);
                startActivity(startIntent);
            }
        });

    }
}
