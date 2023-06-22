package com.namangupta.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Player Representation
    //0-X
    //1-o
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //0-X
    //1-o
    //2-Null
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void TapTap(View view) {
        count+=1;
        ImageView img = (ImageView) view;//created an image view to get tag of the image
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
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap To Play");

            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap To Play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        // Check if any Player Won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                //SomeBody has won
                String winnerStr;
                gameActive=false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has Won";
                } else {
                    winnerStr = "O has Won";
                }
                //Update the status bar for the winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
        if (count == 9)
        {
            String drawStr = " it's a draw ..Play Again";
            TextView status = findViewById(R.id.status);
            status.setText(drawStr);

            gameActive = false;

        }



    }

    public void gameReset(View view){
        count=0;
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length;i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap To Play");
    }
}




