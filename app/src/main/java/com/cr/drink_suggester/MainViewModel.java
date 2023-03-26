package com.cr.drink_suggester;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    MutableLiveData<Integer> mProgressMutableData = new MutableLiveData<>();
    MutableLiveData<String> mDrinksMutableData = new MutableLiveData<>();

    MainRepository mMainRepository;

    public MainViewModel() {
        mProgressMutableData.postValue(View.INVISIBLE);
        mDrinksMutableData.postValue("");
        mMainRepository = new MainRepository();
        String lastSuggestedDrink = mMainRepository.lastSuggestedDrink();
        if (lastSuggestedDrink != null){
            mDrinksMutableData.postValue(lastSuggestedDrink);
        }
    }

    public void suggestNewDrink(){
        mProgressMutableData.postValue(View.VISIBLE);
        mMainRepository.suggestNewDrink(new MainRepository.IDrinkCallback() {
            @Override
            public void onDrinkSuggested(String drinkName) {
                mProgressMutableData.postValue(View.INVISIBLE);
                mDrinksMutableData.postValue(drinkName);
            }

            @Override
            public void onErrorOccurred() {
                mProgressMutableData.postValue(View.INVISIBLE);
                // Show toast with error
            }
        });
    }

    public LiveData<Integer> getProgress(){
        return mProgressMutableData;
    }

    public LiveData<String> getDrink(){
        return mDrinksMutableData;
    }


}
