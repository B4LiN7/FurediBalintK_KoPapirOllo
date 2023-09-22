package com.example.kopapirollo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPlayer;
    private ImageView imgComputer;
    private TextView textViewStatics;
    private Button btnRock;
    private Button btnPaper;
    private Button btnScissors;
    private AlertDialog.Builder alertGameOver;

    private Random rnd = new Random();
    private String playerOption = "";
    private String computerOption = "";
    private int playerPoints = 0;
    private int computerPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(imgPlayer, "rock");
                playerOption = "rock";
                computerRandomChoice();
                decideWinner();
                updateTextViewStatics();
                checkFinalWinner();
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(imgPlayer, "paper");
                playerOption = "paper";
                computerRandomChoice();
                decideWinner();
                updateTextViewStatics();
                checkFinalWinner();
            }
        });

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(imgPlayer, "scissors");
                playerOption = "scissors";
                computerRandomChoice();
                decideWinner();
                updateTextViewStatics();
                checkFinalWinner();
            }
        });
    }

    private void decideWinner() {
        switch (playerOption) {
            case "rock":
                switch (computerOption) {
                    case "rock":
                        verboseWinner("");
                        break;
                    case "paper":
                        verboseWinner("computer");
                        break;
                    case "scissors":
                        verboseWinner("player");
                        break;
                }
                break;
            case "paper":
                switch (computerOption) {
                    case "rock":
                        verboseWinner("player");
                        break;
                    case "paper":
                        verboseWinner("");
                        break;
                    case "scissors":
                        verboseWinner("computer");
                        break;
                }
                break;
            case "scissors":
                switch (computerOption) {
                    case "rock":
                        verboseWinner("computer");
                        break;
                    case "paper":
                        verboseWinner("player");
                        break;
                    case "scissors":
                        verboseWinner("");
                        break;
                }
                break;
        }
    }

    private void verboseWinner(String winner) {
        if (winner.equals("player")) {
            Toast.makeText(MainActivity.this, "Játékos nyert", Toast.LENGTH_LONG).show();
            playerPoints++;
        }
        else if (winner.equals("computer")) {
            Toast.makeText(MainActivity.this, "Számitógép nyert", Toast.LENGTH_LONG).show();
            computerPoints++;
        }
        else {
            Toast.makeText(MainActivity.this, "Döntetlen", Toast.LENGTH_LONG).show();
        }
    }

    private void checkFinalWinner() {
        if (playerPoints >= 3) {
            alertGameOver.setTitle("Győzelem").create();
            alertGameOver.show();
        }
        else if (computerPoints >= 3) {
            alertGameOver.setTitle("Vereség").create();
            alertGameOver.show();
        }
    }

    private void computerRandomChoice() {
        switch (rnd.nextInt(3)) {
            case 0:
                setImage(imgComputer, "rock");
                computerOption = "rock";
                break;
            case 1:
                setImage(imgComputer, "paper");
                computerOption = "paper";
                break;
            case 2:
                setImage(imgComputer, "scissors");
                computerOption = "scissors";
                break;
        }
    }

    private void updateTextViewStatics() {
        textViewStatics.setText("Eredmény: Ember: " + playerPoints + ", Számitógép: " + computerPoints);
    }

    private void setImage(ImageView imageView, String image) {
        switch (image) {
            case "rock":
                imageView.setImageResource(R.drawable.rock);
                break;
            case "paper":
                imageView.setImageResource(R.drawable.paper);
                break;
            case "scissors":
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
    }

    private void newGame() {
        playerPoints = 0;
        computerPoints = 0;
        updateTextViewStatics();

        playerOption = "";
        computerOption = "";

        setImage(imgPlayer, "rock");
        setImage(imgComputer, "rock");
    }

    private void init() {
        imgPlayer = findViewById(R.id.imgPlayer);
        imgComputer = findViewById(R.id.imgComputer);
        textViewStatics = findViewById(R.id.textViewStatics);
        btnRock = findViewById(R.id.btnRock);
        btnPaper = findViewById(R.id.btnPaper);
        btnScissors = findViewById(R.id.btnScissors);

        alertGameOver = new AlertDialog.Builder(MainActivity.this);
        alertGameOver.setTitle("?")
                .setMessage("Újra akarod kezdeni?").setNegativeButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newGame();
                    }
                }).setPositiveButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setCancelable(false)
                .create();
    }
}