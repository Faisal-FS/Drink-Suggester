package com.cr.mvpapplication;

import android.content.res.AssetManager;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocalDataSource implements IDataSource{

    ArrayList<String> drinksListLocal = null;
    AssetManager manager;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LocalDataSource(AssetManager assetManager) {
        this.manager = assetManager;
        drinksListLocal = new ArrayList<>();
        initializeAssetData();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void initializeAssetData(){
        String assetJsonData = loadJSONData();

        try {
            JSONObject jsonObject = new JSONObject(assetJsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("drinks");

            for (int i = 0; i < jsonArray.length(); i++) {
                drinksListLocal.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String loadJSONData(){
        String jsonData = null;

        try {
            InputStream is = this.manager.open("drinks.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            jsonData = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
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
