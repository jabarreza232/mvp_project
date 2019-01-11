package com.evolutions.jabar.testmagang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evolutions.jabar.testmagang.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{
   private   Context context;
   private ArrayList<String> angka;
    public DataAdapter(Context context,ArrayList<String>angka){
        this.context = context;
        this.angka = angka;
    }



    @Override
    public DataViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.list_angka,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder( DataViewHolder holder, int position) {

        final String angka1 = angka.remove(position);

        holder.txt_angka.setText(angka.get(position));
        holder.txt_angka.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        remove(angka1);
    }
});
    }

    @Override
     public int getItemCount() {
        return angka.size();
    }
 class DataViewHolder extends RecyclerView.ViewHolder{
   @BindView(R.id.txt_angka)
   TextView txt_angka;
        private DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);

        }
    }
    public void add(int position,String item){
        angka.add(position,item);
        notifyItemInserted(position);

    }

    private void remove(String item){
        int position =angka.indexOf(item);
        angka.remove(position);
        notifyItemRemoved(position);

    }
}
