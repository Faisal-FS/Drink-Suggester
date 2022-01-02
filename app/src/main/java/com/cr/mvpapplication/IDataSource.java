package com.cr.mvpapplication;

import java.util.List;

public interface IDataSource {
    interface LoadDataCallback{
        void onDataLoaded(String drinkName);
        void onDataNotAvailable(Exception e);
    }

    interface GetDataCallback{
        void onDataLoaded(String drinkName);
        void onDataNotAvailable(Exception e);
    }

    void suggestNewDrink(LoadDataCallback callback);
    void deleteAllDrinks();
    void deleteDrink(String drinkName);
    void addDrink(String drinkName);
    void addDrinks(List<String> drinks);
}
