package com.palfs.mvcapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String[] drinksList = {"Mint Margarita", "Spiking coffee", "Sweet Bananas", "Tomato Tang", "Apple Berry Smoothie"};

    TextView tvDrinkName;
    ProgressBar progressBar;
    Button bGetDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDrinkName = findViewById(R.id.tvDrinkName);
        progressBar = findViewById(R.id.progressBar);
        bGetDrink = findViewById(R.id.bGetDrink);

        bGetDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suggestDrink();
            }
        });
    }

    private void suggestDrink() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        final String[] drinkName = {""};
        //Before executing background task
        progressBar.setVisibility(View.VISIBLE);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here

                try {
                    Thread.sleep(1000); // Mimic server request / long execution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                drinkName[0] = drinksList[new Random().nextInt(drinksList.length)];

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        tvDrinkName.setText(drinkName[0]);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}