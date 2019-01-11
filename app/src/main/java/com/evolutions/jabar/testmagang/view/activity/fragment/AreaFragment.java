package com.evolutions.jabar.testmagang.view.activity.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.adapter.SubAreaAdapter;
import com.evolutions.jabar.testmagang.api.BaseApiService;
import com.evolutions.jabar.testmagang.api.RetrofitClient;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.iterator.IteratorPresenterArea;
import com.evolutions.jabar.testmagang.model.Response;
import com.evolutions.jabar.testmagang.presenter.Presenter;
import com.evolutions.jabar.testmagang.view.activity.activity.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AreaFragment extends Fragment implements IteratorPresenterArea.view, SubAreaAdapter.Callback {
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.nyoba)
    TextView txt_area;
Presenter presenter;
List<Response.Area> areaList;
BaseApiService mApiService;
SubAreaAdapter adapter;
TinyDB tinyDB;
    public AreaFragment() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mApiService = RetrofitClient.getApiClient().create(BaseApiService.class);
        tinyDB = new TinyDB(getContext());
        presenter= new Presenter(getContext(),this);
        areaList = new ArrayList<>();

        initdata(areaList);
        presenter.showArea();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dimissLoading() {

    }

    @Override
    public void onSuccess(Response area) {
        if(area.isSuccess()){
            areaList= new ArrayList<>();
            areaList = area.getArea();
            adapter = new SubAreaAdapter(getContext(),areaList,this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

        }

    }

    @Override
    public void onFailure(String error) {

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        ButterKnife.bind(this,view);

        return view;

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(Response.Area area) {
        tinyDB.putString(Key.area,area.getName());
        String area1= tinyDB.getString(Key.area);
        txt_area.setText("Kota : "+ area1);
        Toast.makeText(getContext(), area.getName() , Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getContext(), LoginActivity.class);
        i.putExtra("deskripsi",area.getDeskripsi());

        startActivity(i);
    }

    @Override
    public void initdata(List<Response.Area> area) {
        String[] deskripsi = getResources().getStringArray(R.array.deskripsi);
        area.clear();
        for (int i=0; i<10; i++){
             area.add(new Response.Area(deskripsi[i]));

        }
    }

}
