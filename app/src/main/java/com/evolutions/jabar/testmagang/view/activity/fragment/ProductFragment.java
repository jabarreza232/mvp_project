package com.evolutions.jabar.testmagang.view.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.adapter.ProductAdapter;
import com.evolutions.jabar.testmagang.api.BaseApiService;
import com.evolutions.jabar.testmagang.api.RetrofitClient;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.iterator.IteratorPresenterProduct;
import com.evolutions.jabar.testmagang.model.ModelProduct;
import com.evolutions.jabar.testmagang.presenter.PresenterProduct;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class ProductFragment extends Fragment implements IteratorPresenterProduct.view,View.OnClickListener {
RecyclerView recyclerViewProduct;
    BaseApiService mApiService;
    ProductAdapter productAdapter;
    List<ModelProduct.Product> products;
 TextView nama;
    PresenterProduct presenter;
    Context context;
   Button view_pdf;
   TinyDB tinyDB;
    @SuppressLint("ValidFragment")
    public ProductFragment(Context context) {
        this.context = context;
        // Required empty public constructor
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApiService = RetrofitClient.getApiClient().create(BaseApiService.class);
        tinyDB = new TinyDB(getContext());
        presenter = new PresenterProduct(context,this);
        presenter.showProduct();
        view_pdf.setOnClickListener(this);
        String name=  tinyDB.getString(Key.nama);
        nama.setText("Hallo, "+name+" :)");
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        String url = tinyDB.getString(Key.pdf);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dimissLoading() {

    }

    @Override
    public void onSuccess(ModelProduct product) {
        if (product.isSuccess()){
            products = new ArrayList<>();
            RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
            recyclerViewProduct.setLayoutManager(manager);
            recyclerViewProduct.setHasFixedSize(true);
            products = product.getProductList();
            productAdapter = new ProductAdapter(context, products);
            recyclerViewProduct.setAdapter(productAdapter);
        }
    }


    @Override
    public void onFailure(String error) {
        Toast.makeText(context, error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_product, container, false);
        nama=(TextView)view.findViewById(R.id.nama);
        recyclerViewProduct = (RecyclerView)view.findViewById(R.id.recycleview_product);
        view_pdf = (Button)view.findViewById(R.id.view_pdf);
        return view;
    }

}
