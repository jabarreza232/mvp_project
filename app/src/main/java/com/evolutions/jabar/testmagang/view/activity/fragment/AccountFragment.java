package com.evolutions.jabar.testmagang.view.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AccountFragment extends Fragment {
    @BindView(R.id.nama)
    TextView nama;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.level)
    TextView level;
    @BindView(R.id.nomor)
    TextView nomor;
    TinyDB tinyDB;

    public AccountFragment() {
        // Required empty public constructor
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
tinyDB = new TinyDB(getContext());
        String name= tinyDB.getString(Key.nama);
        String email1 =tinyDB.getString(Key.email);
        String level1 = tinyDB.getString(Key.level);
        nama.setText(name);
        email.setText(email1);
        level.setText(level1);
        nomor.setText("+628xxxxxxxx");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this,view);
        return view;
        }

    }
