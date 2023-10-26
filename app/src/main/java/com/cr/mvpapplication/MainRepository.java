package com.cr.mvpapplication;

import java.util.List;

public class MainRepository implements IDataSource{

    private final IDataSource mRemoteDataSource;
    private final IDataSource mLocalDataSource;

    public MainRepository(IDataSource mRemoteDataSource, IDataSource mLocalDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    @Override
    public void suggestNewDrink(LoadDataCallback callback) {
        // Query the local storage if available. If not, query the network.
        mLocalDataSource.suggestNewDrink(new LoadDataCallback() {
            @Override
            public void onDataLoaded(String drinkName) {
                callback.onDataLoaded(drinkName);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                getFromRemoteDataSource(callback);
            }
        });
    }

    @Override
    public void addDrink(String drinkName) {

    }

    private void refreshLocalDataSource(String drinkName) {
        mLocalDataSource.addDrink(drinkName);
    }

    private void getFromRemoteDataSource(LoadDataCallback callback) {
        mRemoteDataSource.suggestNewDrink(new LoadDataCallback() {
            @Override
            public void onDataLoaded(String drinkName) {
                refreshLocalDataSource(drinkName);
                callback.onDataLoaded(drinkName);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                callback.onDataNotAvailable(e);
            }
        });
    }
}

