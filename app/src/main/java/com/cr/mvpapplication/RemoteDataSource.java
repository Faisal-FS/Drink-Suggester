package com.cr.mvpapplication;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class RemoteDataSource implements IDataSource{
    String[] drinksListRemote = {"Spiking coffee", "Sweet Bananas", "Tomato Tang", "Apple Berry Smoothie"};

    @Override
    public void suggestNewDrink(LoadDataCallback callback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Before executing background task

        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                try {
                    Thread.sleep(1000); // Mimic server request / long execution

                    String drinkName = drinksListRemote[new Random().nextInt(drinksListRemote.length)];
                    callback.onDataLoaded(drinkName);

                } catch (InterruptedException e) {
                    callback.onDataNotAvailable(e);
                } catch (Exception e){
                    callback.onDataNotAvailable(e);
                }
            }
        });
    }

    @Override
    public void addDrink(String drinkName) {

    }
}


