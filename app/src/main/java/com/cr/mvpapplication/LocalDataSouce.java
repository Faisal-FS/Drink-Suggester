package com.cr.mvpapplication;

import java.util.List;
import java.util.Random;

public class LocalDataSouce implements IDataSource{

    String[] drinksListLocal = {"Apple Berry Smoothie"};

    @Override
    public void suggestNewDrink(LoadDataCallback callback) {
        try {

            String drinkName = drinksListLocal[new Random().nextInt(drinksListLocal.length)];
            callback.onDataLoaded(drinkName);
        } catch (Exception e) {
            callback.onDataNotAvailable(e);
        }
    }

    @Override
    public void deleteAllDrinks() {

    }

    @Override
    public void deleteDrink(String drinkName) {

    }

    @Override
    public void addDrink(String drinkName) {

    }

    @Override
    public void addDrinks(List<String> drinks) {

    }
}
