package com.example.utilisateur.apptest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button bttn1;
    private Button bttn2;
    private Button bttn3;
    private Button bttn4;
    private Button bttn5;
    private Button bttn6;
    private Button bttn7;
    private Button bttn8;
    private Button bttn9;

    ArrayList<Button> board = new ArrayList<>();

    private TextView currentTV;
    private TextView oScoreTV;
    private TextView xScoreTV;

    private String player;
    private boolean goingOn;

    private int oScore;
    private int xScore;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        this.bttn1 = (Button) findViewById(R.id.button1);
        this.bttn1.setOnClickListener(clickListener);
        this.bttn2 = (Button) findViewById(R.id.button2);
        this.bttn2.setOnClickListener(clickListener);
        this.bttn3 = (Button) findViewById(R.id.button3);
        this.bttn3.setOnClickListener(clickListener);
        this.bttn4 = (Button) findViewById(R.id.button4);
        this.bttn4.setOnClickListener(clickListener);
        this.bttn5 = (Button) findViewById(R.id.button5);
        this.bttn5.setOnClickListener(clickListener);
        this.bttn6 = (Button) findViewById(R.id.button6);
        this.bttn6.setOnClickListener(clickListener);
        this.bttn7 = (Button) findViewById(R.id.button7);
        this.bttn7.setOnClickListener(clickListener);
        this.bttn8 = (Button) findViewById(R.id.button8);
        this.bttn8.setOnClickListener(clickListener);
        this.bttn9 = (Button) findViewById(R.id.button9);
        this.bttn9.setOnClickListener(clickListener);

        this.board.add(this.bttn1);
        this.board.add(this.bttn2);
        this.board.add(this.bttn3);
        this.board.add(this.bttn4);
        this.board.add(this.bttn5);
        this.board.add(this.bttn6);
        this.board.add(this.bttn7);
        this.board.add(this.bttn8);
        this.board.add(this.bttn9);

        this.oScoreTV = (TextView) findViewById(R.id.oScoreTV);
        this.xScoreTV = (TextView) findViewById(R.id.xScoreTV);
        this.xScoreTV.setTextColor(Color.GRAY);
        this.oScoreTV.setTextColor(Color.GRAY);

        this.oScore = 0;
        this.xScore = 0;

        findViewById(R.id.buttonResetTurn).setOnClickListener(resetTurnListener);
        findViewById(R.id.buttonResetGame).setOnClickListener(resetGameListener);

        this.currentTV = (TextView) findViewById(R.id.currentTV);
        this.player = "X";

        this.goingOn = true;

        this.count = 1;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            if(goingOn == true && ((Button) v).getText().equals("")){
                ((Button) v).setText(player);
                if(checkVictory()){
                    goingOn = false;
                    currentTV.setText("THE WINNER IS " + player + "!");

                    if(player.equals("X")) xScore++; xScoreTV.setText("X : " + xScore);
                    if(player.equals("O")) oScore++; oScoreTV.setText("O : " + oScore);
                    count++;
                    setScoreColors();
                }else {
                    if (checkDraw()) {
                        goingOn = false;
                        currentTV.setText("THE GAME IS A DRAW!");
                        count++;
                        setScoreColors();
                    } else {
                        toggleCurrent();
                    }
                }
            }
        }
    };

    View.OnClickListener resetTurnListener = new View.OnClickListener() {
        public void onClick(View v) {
            goingOn = true;
            if(count%2 == 0){
                player = "O";
                currentTV.setText("CURRENT PLAYER IS O");
            }
            else {
                player = "X";
                currentTV.setText("CURRENT PLAYER IS X");
            }
            bttn1.setText("");
            bttn2.setText("");
            bttn3.setText("");
            bttn4.setText("");
            bttn5.setText("");
            bttn6.setText("");
            bttn7.setText("");
            bttn8.setText("");
            bttn9.setText("");
            for(int i=0; i<board.size(); i++){
                board.get(i).setTextColor(Color.BLACK);
            }
        }
    };

    View.OnClickListener resetGameListener = new View.OnClickListener() {
        public void onClick(View v) {
            goingOn = true;
            player = "X";
            currentTV.setText("CURRENT PLAYER IS X");
            bttn1.setText("");
            bttn2.setText("");
            bttn3.setText("");
            bttn4.setText("");
            bttn5.setText("");
            bttn6.setText("");
            bttn7.setText("");
            bttn8.setText("");
            bttn9.setText("");
            for(int i=0; i<board.size(); i++){
                board.get(i).setTextColor(Color.BLACK);
            }
            oScore = 0;
            xScore = 0;
            xScoreTV.setText("X : 0");
            oScoreTV.setText("O : 0");
            xScoreTV.setTextColor(Color.GRAY);
            oScoreTV.setTextColor(Color.GRAY);
            count = 1;
        }
    };


    private void setScoreColors(){
        if(this.xScore == this.oScore){
            xScoreTV.setTextColor(Color.GRAY);
            oScoreTV.setTextColor(Color.GRAY);
        }
        if(this.xScore < this.oScore){
            xScoreTV.setTextColor(Color.LTGRAY);
            oScoreTV.setTextColor(Color.BLACK);
        }
        if(this.xScore > this.oScore){
            xScoreTV.setTextColor(Color.BLACK);
            oScoreTV.setTextColor(Color.LTGRAY);
        }
    }

    private void toggleCurrent(){
        switch(this.player) {
            case "X":
                this.player = "O";
                break;
            case "O":
                this.player = "X";
                break;
        }
        this.currentTV.setText("CURRENT PLAYER IS " + this.player);
    }

    private boolean checkVictory(){
        if(checkLine(0, 1, 2)) return true;
        if(checkLine(3, 4, 5)) return true;
        if(checkLine(6, 7, 8)) return true;
        if(checkLine(0, 3, 6)) return true;
        if(checkLine(1, 4, 7)) return true;
        if(checkLine(2, 5, 8)) return true;
        if(checkLine(0, 4, 8)) return true;
        if(checkLine(2, 4, 6)) return true;
        return false;
    }

    private boolean checkDraw(){
        for(int i=0; i<this.board.size(); i++){
            if(this.board.get(i).getText().equals("")){
                return false;
            }
        }
        for(int i=0; i<board.size(); i++){
            board.get(i).setTextColor(Color.LTGRAY);
        }
        return true;
    }

    private boolean checkLine(int one, int two, int three){
        if(this.board.get(one).getText().equals(this.board.get(two).getText())){
            if(this.board.get(two).getText().equals(this.board.get(three).getText())){
                if(this.board.get(one).getText().equals("")){
                    return false;
                }
                else{
                    for(int i=0; i<this.board.size(); i++){
                        this.board.get(i).setTextColor(Color.LTGRAY);
                    }
                    this.board.get(one).setTextColor(Color.BLUE);
                    this.board.get(two).setTextColor(Color.BLUE);
                    this.board.get(three).setTextColor(Color.BLUE);
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
