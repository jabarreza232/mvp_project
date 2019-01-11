package com.evolutions.jabar.testmagang.iterator;

import com.evolutions.jabar.testmagang.model.Response;

public interface IteratorPresenterArea {
    interface view{
        void showLoading();
        void dimissLoading();
        void onSuccess(Response area);
        void onFailure(String error);
    }

    interface show{
        void showArea();
    }



}
