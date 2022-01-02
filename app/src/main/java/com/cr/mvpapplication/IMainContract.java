package com.cr.mvpapplication;

public interface IMainContract {
    interface View extends BaseView<Presenter>{
        void showProgress();
        void hideProgress();
        void showDrinkToUser(String drinkName);
    }

    interface Presenter extends BasePresenter{
        void suggestDrink();
    }
}
