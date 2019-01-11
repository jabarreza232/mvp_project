package com.evolutions.jabar.testmagang.view.activity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.view.activity.fragment.AccountFragment;
import com.evolutions.jabar.testmagang.view.activity.fragment.AreaFragment;
import com.evolutions.jabar.testmagang.view.activity.fragment.ProductFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{
//    RecyclerView recyclerView, recyclerViewProduct;
//    BaseApiService mApiService;
//    SubAreaAdapter adapter;
//    List<Response.Area> area1;
//    ProductAdapter productAdapter;
//    List<ModelProduct.Product> products;
//    Presenter presenter;
TinyDB tinyDB;
    @BindView(R.id.bottom_navgation)BottomNavigationView bottomNavigationView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        setTitle("");
tinyDB = new TinyDB(this);
        //        String token = Preferences.getTOKEN(this);
//        presenter = new Presenter(this, this, this, mApiService);
//        presenter.showArea(token);
//        presenter.showProduct(token);
//        CircleImageView img_profile = (CircleImageView) findViewById(R.id.img_profile);
//        String image =Preferences.getIMAGE(this);
//        Glide.with(this).load(image).into(img_profile);
//        TextView user = (TextView) findViewById(R.id.nama);
//        TextView email = (TextView) findViewById(R.id.email);
//        String user1 = Preferences.getNAME(this);
//        String email1 = Preferences.getEMAIL(this);
//        user.setText("Hallo ," + user1 + " :)");
//        email.setText("Email : " + email1);
//        Button view_pdf = (Button)findViewById(R.id.view_pdf);
//        view_pdf.setOnClickListener(this);
        ButterKnife.bind(this);

        loadFragment(new ProductFragment(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    Fragment fragment = null;
    switch (item.getItemId()){
        case R.id.product:
            fragment = new ProductFragment(this);
            break;
        case R.id.area1:
            fragment = new AreaFragment();
            break;
        case R.id.account:
            fragment= new AccountFragment();
            break;

    }


        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container,fragment)
                    .commit();
            return true;
        }
    return  false;
    }
//
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void dimissLoading() {
//
//    }
//
//    @Override
//    public void onSuccess(ModelProduct product) {
//        if (product.isSuccess()) {
//            products = new ArrayList<>();
//            mApiService = RetrofitClient.getApiClient().create(BaseApiService.class);
//            recyclerViewProduct = (RecyclerView) findViewById(R.id.recycleview_product);
//            RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
//            recyclerViewProduct.setLayoutManager(manager);
//            recyclerViewProduct.setHasFixedSize(true);
//            products = product.getProductList();
//            productAdapter = new ProductAdapter(this, products);
//            recyclerViewProduct.setAdapter(productAdapter);
//        }
//
//
//    }
//
//    @Override
//    public void onSuccess(Response area) {
//        if (area.isSuccess()) {
//            area1 = new ArrayList<>();
//            mApiService = RetrofitClient.getApiClient().create(BaseApiService.class);
//            recyclerView = (RecyclerView) findViewById(R.id.recycleview);
//            RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
//            recyclerView.setLayoutManager(manager);
//            recyclerView.setHasFixedSize(true);
//            area1 = area.getArea();
//            adapter = new SubAreaAdapter(AreaActivity.this, area1);
//            recyclerView.setAdapter(adapter);
//        }
//
//    }
//
//    @Override
//    public void onFailure(String error) {
//        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
//    }
//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                tinyDB.clear();
                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);
                finish();
                break;

        }

        return true;

    }
//
//    @Override
//    public void onClick(View view) {
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(Preferences.getPDF(this)));
//
//        startActivity(i);
//
//    }

}
