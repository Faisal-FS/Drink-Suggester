package com.cr.mvpapplication;

public class MainPresenter implements IPresenter{

    IView view;
    MainRepository repository;

    MainPresenter(IView view){
        this.view = view;
        repository = new MainRepository(this);
    }

    @Override
    public void suggestDrink() {
        view.showProgress();
        repository.suggestNewDrink();
    }

    @Override
    public void onDrinkSuggested(String drinkName) {
        view.hideProgress();
        view.showDrinkToUser(drinkName);
    }
}
