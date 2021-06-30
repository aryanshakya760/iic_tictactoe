package com.iic.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;

    int activePlayer = 0;
    //Using array
    int [] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //Using 2d array on grid
    int [] [] winPosition = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};


    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        //takes the tag and converts into string
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                //Setting Text
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                //Setting Text
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
        img.animate().translationYBy(1000f).setDuration(300);
        }

        //Check if any player has won
        //Using for loop
        for(int[] winPositions: winPosition){
            if(gameState[winPositions[0]] == gameState[winPositions[1]] &&
                gameState[winPositions[1]] == gameState[winPositions[2]] &&
                gameState[winPositions[0]]!=2) {
                //Somebody won! Find out who
                String winner;
                gameActive = false;
                if (gameState[winPositions[0]] == 0) {
                    winner = "X has won";
                } else {
                    winner = "O has won";
                }
                //Update status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winner);
            }
        }

    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.a1)).setImageResource(0);
        ((ImageView)findViewById(R.id.a2)).setImageResource(0);
        ((ImageView)findViewById(R.id.a3)).setImageResource(0);
        ((ImageView)findViewById(R.id.a4)).setImageResource(0);
        ((ImageView)findViewById(R.id.a5)).setImageResource(0);
        ((ImageView)findViewById(R.id.a6)).setImageResource(0);
        ((ImageView)findViewById(R.id.a7)).setImageResource(0);
        ((ImageView)findViewById(R.id.a8)).setImageResource(0);
        ((ImageView)findViewById(R.id.a9)).setImageResource(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}