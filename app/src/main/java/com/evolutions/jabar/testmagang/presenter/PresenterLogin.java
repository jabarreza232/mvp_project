package com.evolutions.jabar.testmagang.presenter;

import android.content.Context;
import android.util.Log;

import com.evolutions.jabar.testmagang.api.BaseApiService;
import com.evolutions.jabar.testmagang.api.RetrofitClient;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.iterator.IteratorLogin;
import com.evolutions.jabar.testmagang.model.ModelLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogin implements IteratorLogin.presenter {
     public Context context;
     private  IteratorLogin.view view;
     private    BaseApiService mApiService;
    TinyDB tinyDB;
    public PresenterLogin(Context context, IteratorLogin.view view) {
        this.context = context;
        this.view = view;
        mApiService = RetrofitClient.getApiClient().create(BaseApiService.class);
        tinyDB = new TinyDB(context);
    }


    @Override
    public void onLogin(final String nik, final String password) {

                        Call<ModelLogin> call = mApiService.login(nik, password);
                        tinyDB.putString(Key.nik,nik);
                        call.enqueue(new Callback<ModelLogin>() {
                        @Override
                        public void onResponse(Call<ModelLogin> call, Response<ModelLogin> response) {
                            view.showLoading();
                        if (response.isSuccessful()) {
                            ModelLogin modelLogin = response.body();
                            view.onSuccess(modelLogin);

                            //
////
////                        if (modelLogin.isStatus()==true) {
////                            Toast.makeText(context, "Berhasil Login !", Toast.LENGTH_SHORT).show();
////                            String token = modelLogin.getToken();
////                            Intent i = new Intent(context, AreaActivity.class);
////                            i.putExtra("token", token);
////                            startActivity(i);
////
////
////                        } else if(modelLogin.isStatus()==false){
////                            String pesan_gagal = "Gagal Login";
////
////                            Toast.makeText(context, pesan_gagal, Toast.LENGTH_SHORT).show();
////
////                        }

                       } else {
                       String error = "Login Gagal";
                       view.onFailure(error);
                }
                            view.dismissLoading();

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }


//
//                if (nik.equals("123") && password.equals("123")) {
//                    String notice = "Bilangan Genap : ";
//
//                    int[] list = new int[]{0, 2, 4, 6, 8, 10};
//
//                    for (int i = 0; i<list.length; i++) {
//
//                    notice += list[i] + ","/*+list[1]+ ","+list[2]+ ","+list[3]+ ","+list[4]+ ","+list[5]*/;
//
//                    }
//
//                    view.onSuccess(notice);
//
//                } else if (nik.equals("1234") && password.equals("1234")) {
//                    String notice = "Bilangan Ganjil : ";
//                    int[] list = new int[]{1, 3, 5, 7, 9, 11};
//                    for (int i = 0; i < list.length; i++) {
//                    notice += list[i] + ","/*+list[]+ ","+list[2]+ ","+list[3]+ ","+list[4]+ ","+list[5]*/;
//                    }
//                    view.onSuccess(notice);
//
//
//                }
            });


        }



    }

