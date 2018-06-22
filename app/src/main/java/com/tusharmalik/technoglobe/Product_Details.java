package com.tusharmalik.technoglobe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.tusharmalik.technoglobe.Adapters.BuyerAdapter;
import com.tusharmalik.technoglobe.Adapters.DetailAdapter;
import com.tusharmalik.technoglobe.Models.Seller;

import java.util.ArrayList;

public class Product_Details extends AppCompatActivity {
    Button btnBack,btnCart,btnPay;
    RecyclerView recyclerView;
    DetailAdapter detailAdapter;
    TextView detailName;
    ArrayList<Seller> records = new ArrayList<Seller>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__details);

        final Spinner dropdown = findViewById(R.id.detailspinner);
        detailName=findViewById(R.id.detailName);
        btnPay=findViewById(R.id.DetailBUY);
        btnCart=findViewById(R.id.DetailADDTOCART);
        btnBack=findViewById(R.id.buttonBACK);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Product_Details.this,Payment.class);
                startActivity(i);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Product_Details.this,BuyerActivity.class);
                startActivity(i);
            }
        });
        String[] items = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20(max)"};
        dropdown.setSelection(0);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        final String[] item = new String[1];

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                item[0] = (String) dropdown.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        recyclerView=(RecyclerView) findViewById(R.id.detail);
        Intent mIntent = getIntent();
        int pos = mIntent.getIntExtra("pos", 0);

        detailAdapter.getItem(pos);


        DividerItemDecoration itemDecoration5= new DividerItemDecoration(this, new LinearLayoutManager(Product_Details.this).getOrientation());
        recyclerView.setLayoutManager(new LinearLayoutManager(Product_Details.this));
        recyclerView.addItemDecoration(itemDecoration5);
        recyclerView.setHasFixedSize(true);
        detailAdapter = new DetailAdapter(records, getBaseContext());
        recyclerView.setAdapter(detailAdapter);
        detailAdapter.notifyDataSetChanged();


    }
}
