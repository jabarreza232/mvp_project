package com.evolutions.jabar.testmagang.iterator;

import com.evolutions.jabar.testmagang.model.ModelProduct;
import com.evolutions.jabar.testmagang.model.Response;

public interface IteratorPresenterProduct {
    interface view{
        void showLoading();
        void dimissLoading();
        void onSuccess(ModelProduct product);
        void onFailure(String error);

    }


    interface show{
        void showProduct();

    }

}
