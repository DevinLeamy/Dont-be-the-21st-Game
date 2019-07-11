package com.example.myfirstgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_page);
//Launch Page Buttons
        final Button pVPBtn = findViewById(R.id.pVPBtn);
        final Button hardBtn = findViewById(R.id.hardBtn);
        final Button mediumBtn = findViewById(R.id.mediumBtn);
        final Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
//Player vs Player Button
        pVPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.putExtra("buttonValues", "0");
                startIntent.putExtra("howChallenging", "none");
                startActivity(startIntent);
            }
        });
//Difficulty (Hard) Button
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.putExtra("buttonValues", "0");
                startIntent.putExtra("howChallenging", "hard");
                startActivity(startIntent);
            }
        });
//Difficulty (Medium) Button
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent.putExtra("buttonValues", "0");
                startIntent.putExtra("howChallenging", "medium");
                startActivity(startIntent);
            }
        });
    }
}
