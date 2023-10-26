package com.cr.mvpapplication;


public interface IDataSource {
    interface LoadDataCallback{
        void onDataLoaded(String drinkName);
        void onDataNotAvailable(Exception e);
    }

    void suggestNewDrink(LoadDataCallback callback);
    void addDrink(String drinkName);
}

