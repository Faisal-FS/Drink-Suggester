package com.cr.mvpapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainRepository implements IDataSource{

    private final IDataSource mRemoteDataSource;
    private final IDataSource mLocalDataSource;
    private List<String> drinksCache;

    boolean isCacheDirty;

    public MainRepository(IDataSource mRemoteDataSource, IDataSource mLocalDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }


    @Override
    public void suggestNewDrink(LoadDataCallback callback) {
        String cachedDrink = null;
        if (drinksCache != null && !isCacheDirty){
            cachedDrink = getCacheDrink();
        }

        if (cachedDrink != null)
            callback.onDataLoaded(cachedDrink);

        if (isCacheDirty){
            // If the cache is dirty we need to fetch new data from the network.
            getFromRemoteDataSource(callback);
        }else{
            // Query the local storage if available. If not, query the network.
            mLocalDataSource.suggestNewDrink(new LoadDataCallback() {
                @Override
                public void onDataLoaded(String drinkName) {
                    refreshCache(drinkName);
                    callback.onDataLoaded(drinkName);
                }

                @Override
                public void onDataNotAvailable(Exception e) {
                    getFromRemoteDataSource(callback);
                }
            });
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

    private void refreshLocalDataSource(String drinkName) {
        mLocalDataSource.addDrink(drinkName);
    }

    private void refreshCache(String drinkName){
        if (drinksCache == null)
            drinksCache = new ArrayList<>();

        // Instead of this cache is used to extract data based upon id fast
        // Update with id logic and use hashmap instead

        if (!drinksCache.contains(drinkName))
            drinksCache.add(drinkName);
    }

    private String getCacheDrink(){
        if (drinksCache.size() > 0)
            return drinksCache.get(new Random().nextInt(drinksCache.size() - 1));
        return null;
    }

    private void getFromRemoteDataSource(LoadDataCallback callback) {
        mRemoteDataSource.suggestNewDrink(new LoadDataCallback() {
            @Override
            public void onDataLoaded(String drinkName) {
                refreshCache(drinkName);
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
