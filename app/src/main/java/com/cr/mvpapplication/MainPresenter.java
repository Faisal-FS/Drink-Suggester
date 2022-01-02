package com.cr.mvpapplication;

public class MainPresenter implements IMainContract.Presenter {

    IMainContract.View view;
    MainRepository repository;

    MainPresenter(MainRepository repository, IMainContract.View view){
        this.view = view;
        this.view.setPresenter(this);
        this.repository = repository;
    }

    @Override
    public void suggestDrink() {
        view.showProgress();
        repository.suggestNewDrink(new IDataSource.LoadDataCallback() {
            @Override
            public void onDataLoaded(String drinkName) {
                view.hideProgress();
                view.showDrinkToUser(drinkName);
            }

            @Override
            public void onDataNotAvailable(Exception e) {
                view.hideProgress();
            }

        });
    }

    @Override
    public void start() {
        // Initial logic implementation when the activity starts
        suggestDrink();
    }
}
