package com.evolutions.jabar.testmagang.presenter;

import android.content.Context;
import android.util.Log;

import com.evolutions.jabar.testmagang.api.BaseApiService;
import com.evolutions.jabar.testmagang.api.RetrofitClient;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.iterator.IteratorPresenterArea;
import com.evolutions.jabar.testmagang.iterator.IteratorPresenterProduct;
import com.evolutions.jabar.testmagang.model.ModelProduct;
import com.evolutions.jabar.testmagang.model.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class PresenterProduct implements IteratorPresenterProduct.show {
    public Context context;
    public  IteratorPresenterProduct.view view;
    public BaseApiService apiService;
TinyDB tinyDB;
    public PresenterProduct(Context context, IteratorPresenterProduct.view view) {
        this.context = context;
        this.view = view;
        apiService = RetrofitClient.getApiClient().create(BaseApiService.class);
tinyDB = new TinyDB(context);
    }



    @Override
    public void showProduct() {
        String token = tinyDB.getString(Key.token);
        Call<ModelProduct>call = apiService.request("Bearer "+token);
        call.enqueue(new Callback<ModelProduct>() {

            @Override
            public void onResponse(Call<ModelProduct> call, retrofit2.Response<ModelProduct> response) {
                if (response.isSuccessful()){
                    ModelProduct product = response.body();
                    view.onSuccess(product);
                }else{
                    String gagal = "Response Gagal !";
                    view.onFailure(gagal);

                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable t) {

            }
        });

    }

}

