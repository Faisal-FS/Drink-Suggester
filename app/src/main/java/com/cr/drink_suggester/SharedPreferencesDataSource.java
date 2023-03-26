package com.cr.drink_suggester;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesDataSource {

    static SharedPreferencesDataSource instance;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static SharedPreferencesDataSource getInstance() {
        if (instance == null)
            instance = new SharedPreferencesDataSource();
        return instance;
    }

    public void init(Context context){
        sharedPreferences = context.getSharedPreferences("drinkSuggesterPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setSuggestedDrink(String drinkName){
        editor.putString("last_suggested_drink", drinkName);
        editor.commit();
    }

    public String getLastSuggestedDrink(){
        String drinkName = sharedPreferences.getString("last_suggested_drink", null);
        return drinkName;
    }
}
