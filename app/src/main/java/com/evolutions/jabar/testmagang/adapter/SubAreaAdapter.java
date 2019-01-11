package com.evolutions.jabar.testmagang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.evolutions.jabar.testmagang.helper.Key;
import com.evolutions.jabar.testmagang.helper.TinyDB;
import com.evolutions.jabar.testmagang.model.Response;
import com.evolutions.jabar.testmagang.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubAreaAdapter extends RecyclerView.Adapter<SubAreaAdapter.SubAreaViewHolder> {
    public List<Response.Area> data;
    private Context context;
    Callback callback;
    TinyDB tinyDB;
    public SubAreaAdapter(Context context,List<Response.Area>data,Callback callback){
        this.data= data;
        this.context= context;
        this.callback = callback;
        tinyDB = new TinyDB(context);

    }


    @NonNull
    @Override
    public SubAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
 return new SubAreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAreaViewHolder holder, int position) {
        final Response.Area response = data.get(position);
        holder.txt_nama.setText(response.getName());
        holder.deskripsi.setText(response.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
class SubAreaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.area)
        TextView txt_nama;
        @BindView(R.id.click)
        CardView cardView;
        @BindView(R.id.deskripsi)
        TextView deskripsi;
     public   SubAreaViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            cardView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    callback.onClick(data.get(position));

                }
            });
        }
    }


   public interface Callback{
        void onClick(Response.Area area);
        void initdata(List<Response.Area> area);
    }

}
