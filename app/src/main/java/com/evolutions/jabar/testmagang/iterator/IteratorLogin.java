package com.evolutions.jabar.testmagang.iterator;

import com.evolutions.jabar.testmagang.model.ModelLogin;

public interface IteratorLogin {
    interface view{
        void showLoading();
        void dismissLoading();
        void onSuccess(ModelLogin response);
        void onFailure(String error);
        /*
        void onErrorConnection(String param);
        */
    }
    interface presenter{
        void onLogin(String nik,String password);
    }

}
