package com.cr.mvpapplication;

import java.util.ArrayList;
import java.util.Random;



public class LocalDataSource implements IDataSource{

    ArrayList<String> drinksListLocal = null;

    public LocalDataSource() {
        drinksListLocal = new ArrayList<>();
    }

    @Override
    public void suggestNewDrink(LoadDataCallback callback) {
        try {

            if (drinksListLocal.size() == 0) {
                callback.onDataNotAvailable(new Exception("Not available"));
                return;
            }

            String drinkName = drinksListLocal.get(new Random().nextInt(drinksListLocal.size()));
            callback.onDataLoaded(drinkName);
        } catch (Exception e) {
            callback.onDataNotAvailable(e);
        }
    }

    @Override
    public void addDrink(String drinkName) {
        drinksListLocal.add(drinkName);
    }
}

