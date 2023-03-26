package com.cr.drink_suggester;

import android.app.Application;

public class DrinkSuggesterApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferencesDataSource.getInstance().init(getApplicationContext());
    }
}
