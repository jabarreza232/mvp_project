package com.evolutions.jabar.testmagang.view.activity.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.api.BaseApiService;
import com.evolutions.jabar.testmagang.api.RetrofitClient;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.iterator.IteratorLogin;
import com.evolutions.jabar.testmagang.model.ModelLogin;
import com.evolutions.jabar.testmagang.presenter.PresenterLogin;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements IteratorLogin.view, View.OnClickListener {
@BindView(R.id.edt_nik)
    EditText edtNik;
@BindView(R.id.edt_pass)
        EditText edtPass;
@BindView(R.id.btnLogin)
    Button btnLogin;

    TinyDB tinyDB;
    Context mContext;
    ProgressDialog progressDialog;
    BaseApiService mApiService;
    PresenterLogin presenterLogin;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        setTitle("");
        tinyDB = new TinyDB(this);
        ButterKnife.bind(this);
        mApiService = RetrofitClient.getApiClient().create(BaseApiService.class);
        presenterLogin = new PresenterLogin(this, this);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        presenterLogin.onLogin(edtNik.getText().toString(), edtPass.getText().toString());

    }


    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
        progressDialog.dismiss();

    }

    @Override
    public void dismissLoading() {
        progressDialog.dismiss();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (tinyDB.getBoolean(Key.status)) {
            startActivity(new Intent(getBaseContext(), AreaActivity.class));
            finish();
            }

    }

    @Override
    public void onSuccess(ModelLogin response) {

/*
        Toast.makeText(this, notice, Toast.LENGTH_SHORT).show();
*/
        if (response.isStatus()) {
            Toast.makeText(this, "Berhasil Login !", Toast.LENGTH_SHORT).show();
            tinyDB.getString(Key.nik);
             Intent i = new Intent(this, AreaActivity.class);
             String token = response.getToken();
             String name = response.getName();
             String email = response.getEmail();
             String level = response.getLevel();
             boolean status = response.isStatus();
             tinyDB.putString(Key.token,token);
             tinyDB.putString(Key.email,email);
             tinyDB.putString(Key.level,level);
             tinyDB.putString(Key.nama,name);
             tinyDB.putBoolean(Key.status,status);
             startActivity(i);
        }

    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();


    }


}


