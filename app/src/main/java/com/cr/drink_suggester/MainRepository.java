package com.cr.drink_suggester;

import android.view.View;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRepository {
    String[] drinksListRemote = {"Spiking coffee", "Sweet Bananas", "Tomato Tang",
            "Apple Berry Smoothie", "Coding Reel Coffee"};

    public MainRepository() {
    }

    public String lastSuggestedDrink(){
        return SharedPreferencesDataSource.getInstance().getLastSuggestedDrink();
    }

    public void suggestNewDrink(IDrinkCallback drinkCallback) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //Before executing background task

        executor.execute(new Runnable() {
            @Override
            public void run() {

                //Background work here
                try {
                    Thread.sleep(1000); // Mimic server request / long execution

                    String drinkName = drinksListRemote[new Random()
                            .nextInt(drinksListRemote.length)];
                    setLastSuggestedDrink(drinkName);
                    drinkCallback.onDrinkSuggested(drinkName);
                } catch (InterruptedException e) {
                    drinkCallback.onErrorOccurred();
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                    drinkCallback.onErrorOccurred();
                }
            }
        });
    }

    private void setLastSuggestedDrink(String drinkName) {
        SharedPreferencesDataSource.getInstance().setSuggestedDrink(drinkName);
    }

    public interface IDrinkCallback {
        void onDrinkSuggested(String drinkName);
        void onErrorOccurred();
    }
}
