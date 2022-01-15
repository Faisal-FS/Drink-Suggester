package com.cr.drink_suggester;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    String[] drinksListRemote = {"Spiking coffee", "Sweet Bananas", "Tomato Tang",
            "Apple Berry Smoothie", "Coding Reel Coffee"};


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
                suggestNewDrink();
            }
        });
    }

    public void suggestNewDrink() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Before executing background task
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                try {
                    Thread.sleep(1000); // Mimic server request / long execution

                    String drinkName = drinksListRemote[new Random()
                            .nextInt(drinksListRemote.length)];
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvDrinkName.setText(drinkName);
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}