package com.evolutions.jabar.testmagang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.model.ModelProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    List<ModelProduct.Product> products;
    TinyDB tinyDB;
    public ProductAdapter(Context context, List<ModelProduct.Product> products) {
        this.context = context;
        this.products = products;
        tinyDB = new TinyDB(context);
    }

    @NonNull

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ModelProduct.Product data = products.get(position);
        holder.admin.setText(data.getAdmin());
        holder.sender.setText(data.getSender());
        holder.subject.setText(data.getSubject());
        tinyDB.putString(Key.pdf,data.getFile_pdf());
    }


    @Override
    public int getItemCount() {
        return products.size();


    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
@BindView(R.id.admin)
        TextView admin;
@BindView(R.id.sender)
        TextView sender;
    @BindView(R.id.subject)
    TextView subject;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
