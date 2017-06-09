package com.example.ayah.tictactoegameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 0 = Opponent O, 1 = Opponent X
    int activePlayer  = 0;

    boolean gameIsActive = true;

    int[] gameState =  {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {
            //Horizontal Winning Streak
            {0,1,2},
            {3,4,5},
            {6,7,8},
            //Vertical Winning Streak
            {0,3,6},
            {1,4,7},
            {2,5,8},
            //Diagonal Winning Streak
            {0,4,8},
            {2,4,6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view){
        ImageView center = (ImageView) view;

        //Tapped to check if the counter is already tapped or available
        int tapped = Integer.parseInt(center.getTag().toString());
        if (gameState[tapped] == 2 && gameIsActive) {
            gameState[tapped] = activePlayer;


            center.setTranslationY(-1000f);
            if (activePlayer == 0) {
                center.setImageResource(R.drawable.o);
                activePlayer = 1;

            } else {
                center.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            center.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    String winner = "X";
                    if(gameState[winningPosition[0]] == 0){
                        winner = "O";
                    }

                    gameIsActive = false;

                    //Declaration of winner Dialogue
                    TextView textView = (TextView)findViewById(R.id.textView);
                    textView.setText(winner +" has won!");

                   LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                } else {
                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if (counterState == 2) gameIsOver = false;
                    } if(gameIsOver){
                        TextView textView = (TextView)findViewById(R.id.textView);
                        textView.setText("It is a Draw!");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }


            }

        }
    }


    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;

        }
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }
}
