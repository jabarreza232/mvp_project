package com.evolutions.jabar.testmagang.view.activity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.evolutions.jabar.testmagang.R;
import com.evolutions.jabar.testmagang.adapter.DataAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
TextView name;
RecyclerView recyclerView;
DataAdapter adapter;
Button btnInput,btnLogout;
EditText edt_angka;
ArrayList<String>dataset;
@SuppressLint("SetTextI18n")
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataset = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.list_angka);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new DataAdapter(this,dataset);
        recyclerView.setAdapter(adapter);
        name=(TextView)findViewById(R.id.txt_nama);
        btnLogout = (Button)findViewById(R.id.logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    Spinner spinner = (Spinner)findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> spinnerAdapter= ArrayAdapter.createFromResource(this,R.array.bilangan,android.R.layout.simple_spinner_item);
    spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    String selectedItem = adapterView.getItemAtPosition(i).toString();
    String value1="Bilangan Genap";
    if(selectedItem.equals(value1)){
       Spinner spinner = (Spinner) findViewById(R.id.spinner);
      String item = spinner.getSelectedItem().toString();
      inputAngkaGenap(item);
}
        String value = "Bilangan Ganjil";
        if(selectedItem.equals(value)){
     Spinner spinner = (Spinner) findViewById(R.id.spinner);
     String item = spinner.getSelectedItem().toString();
     inputAngkaGanjil(item);
         }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});



     spinner.setAdapter(spinnerAdapter);
     String nama =getIntent().getStringExtra("nama");
     name.setText("Halo , "+nama + " :)");

    btnInput = (Button)findViewById(R.id.input);
    btnInput.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        for(int i=0;i <= dataset.size();i++) {
            /*
            String data = dataset.get(i);
        ((DataAdapter) adapter).add(dataset.size(), data);
        */
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            spinner.getSelectedItem().toString();

            recyclerView.scrollToPosition(dataset.size()-1);


         }




         /*
            if(dataset.size()%2==0) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                String item = spinner.getItemAtPosition(0).toString();
                inputAngkaGenap(item);
            }else if(dataset.size()%2==1){
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                String item = spinner.getItemAtPosition(1).toString();
                inputAngkaGanjil(item);
            }
*/



        }

    });

}

private void inputAngkaGenap(String item){
    edt_angka = (EditText)findViewById(R.id.edt_angka);
    String input = edt_angka.getText().toString();
    Spinner spinner = (Spinner)findViewById(R.id.spinner);
    item = spinner.getSelectedItem().toString();
  try {
      int nilai = Integer.parseInt(input);
      for(int i=0; i<=nilai; i++){
          if(i%2 ==0) {
              dataset.add( item+" :"+ i);
          }
      }

  }catch (NumberFormatException e){
      e.printStackTrace();
     }
  }
    private void inputAngkaGanjil(String item){
    edt_angka = (EditText)findViewById(R.id.edt_angka);
    String input = edt_angka.getText().toString();
    Spinner spinner = (Spinner)findViewById(R.id.spinner);
    item = spinner.getSelectedItem().toString();

    try {
        int nilai = Integer.parseInt(input);
        for(int i=0; i<=nilai; i++){
            if(i%2 ==1) {
                dataset.add( item+" :"+ i);
            }
        }

    }catch (NumberFormatException e){
        e.printStackTrace();
    }

}

public void onBackPressed(){
    Toast.makeText(this,"Tekan Tombol Logout Untuk Logout account",Toast.LENGTH_SHORT).show();
    }

}


/*
            private List<ModelAngka> proses(){
                edt_angka = (EditText)findViewById(R.id.edt_angka);
                List<ModelAngka> angka = new ArrayList<>();
                String input =  edt_angka.getText().toString();
                int a = Integer.parseInt(input);

           for(int i=0; i<=100;i++){
           if (a%2 ==0){
               ModelAngka angka1 = new ModelAngka();
               angka1.setAngka("");
               angka.add(angka1);

           }else{
        }
   }
return angka;
    }
*/


