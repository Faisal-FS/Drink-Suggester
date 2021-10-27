package com.cr.mvpapplication;

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

public class MainActivity extends AppCompatActivity implements IView{


    TextView tvDrinkName;
    ProgressBar progressBar;
    Button bGetDrink;

    IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDrinkName = findViewById(R.id.tvDrinkName);
        progressBar = findViewById(R.id.progressBar);
        bGetDrink = findViewById(R.id.bGetDrink);

        presenter = new MainPresenter(this);

        bGetDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.suggestDrink();
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showDrinkToUser(String drinkName) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvDrinkName.setText(drinkName);
            }
        });
    }
}