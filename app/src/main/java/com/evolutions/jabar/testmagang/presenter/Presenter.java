package com.evolutions.jabar.testmagang.presenter;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;

import com.evolutions.jabar.testmagang.api.BaseApiService;
import com.evolutions.jabar.testmagang.api.RetrofitClient;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.iterator.IteratorPresenterArea;
import com.evolutions.jabar.testmagang.iterator.IteratorPresenterProduct;
import com.evolutions.jabar.testmagang.model.ModelProduct;
import com.evolutions.jabar.testmagang.model.Response;
import com.evolutions.jabar.testmagang.view.activity.fragment.ProductFragment;

import retrofit2.Call;
import retrofit2.Callback;

public class Presenter implements IteratorPresenterArea.show {
   public Context context;
  public  IteratorPresenterArea.view view;
 public   BaseApiService apiService;
    TinyDB tinyDB;
    public Presenter(Context context, IteratorPresenterArea.view view) {
        this.context = context;
        this.view = view;
        apiService = RetrofitClient.getApiClient().create(BaseApiService.class);
        tinyDB = new TinyDB(context);
    }

    @Override
    public void showArea() {
                String token = tinyDB.getString(Key.token);
               Call<Response> call = apiService.response("Bearer "+ token);
                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.isSuccessful()) {
                            Response response1 = response.body();
                            view.onSuccess(response1);

                        }else{
                            String error="Gagal Merespon!";
                            view.onFailure(error);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.e("debug","onFailure: ERROR > "+t.toString());
                    }
                });

            }


}



