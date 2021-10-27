package com.cr.mvpapplication;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository implements IRepository{
    String[] drinksListRemote = {"Spiking coffee", "Sweet Bananas", "Tomato Tang", "Apple Berry Smoothie"};
    String[] drinksListLocal = {"Mint Margarita"};

    IPresenter presenter;

    boolean isLocalDrinkAvailable;

    public MainRepository(IPresenter presenter) {
        this.presenter = presenter;
        isLocalDrinkAvailable = false;
    }

    void suggestDrinkLocal(){
        String drinkName = drinksListLocal[new Random().nextInt(drinksListLocal.length)];
        presenter.onDrinkSuggested(drinkName);
    }

    void suggestDrinkRemote(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final String[] drinkName = {""};
        //Before executing background task

        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here

                try {
                    Thread.sleep(1000); // Mimic server request / long execution
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                drinkName[0] = drinksListRemote[new Random().nextInt(drinksListRemote.length)];
                presenter.onDrinkSuggested(drinkName[0]);
            }
        });
    }

    @Override
    public void suggestNewDrink() {
        if (isLocalDrinkAvailable) {
            suggestDrinkLocal();
        } else {
            suggestDrinkRemote();
        }
    }
}
